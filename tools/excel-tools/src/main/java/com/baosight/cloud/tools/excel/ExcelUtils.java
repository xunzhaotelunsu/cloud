package com.baosight.cloud.tools.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yang on 2018/5/28.
 */
public class ExcelUtils {

    /**
     * 读取Excel文件做处理
     * @param excelInputStream
     * @param fileType
     * @return
     * @throws IOException
     */
    public static Workbook readAsWorkbook(InputStream excelInputStream, ExcelReader.ExcelFileType fileType) throws IOException{
        ExcelReader reader = new ExcelReader(excelInputStream, fileType);
        return reader.getWorkBook();
    }

    /**
     * 根据sheet名导入Excel指定sheet到List
     *
     * @param excelInputStream
     * @param fileType
     * @param sheetName
     * @param startRowno 读取开始行号
     * @return
     * @throws IOException
     */
    public static List<HashMap<String, Object>> getDataFromExcel(InputStream excelInputStream, ExcelReader.ExcelFileType fileType,
                                                                 String sheetName, int startRowno) throws IOException {
        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        ExcelReader reader = new ExcelReader(excelInputStream, fileType);
        Workbook workbook = reader.getWorkBook();
        Sheet sheet = workbook.getSheet(sheetName);
        // 读取首行获取列名
        Row firstRow = sheet.getRow(startRowno);
        String[] cellName = new String[firstRow.getLastCellNum()];
        for (int i = 0; i < firstRow.getLastCellNum(); i++) {
            Cell cell = firstRow.getCell(i);
            if (cell == null || getCellValue(cell) == null || !StringUtils.hasText(getCellValue(cell).toString())) {
                break;
            }
            String value = getCellValue(cell).toString();
            value = value.replaceAll("\t|\r|\n", "");
            cellName[i] = value;
        }
        // 循环读取sheet
        for (int rowno = startRowno + 1; rowno <= sheet.getLastRowNum(); rowno++) {
            Row row = sheet.getRow(rowno);
            if (row == null) {
                continue;
            }
            boolean emptyFlag = true;
            // 循环读取row的每一列,比第一行多出的部分不读取
            HashMap<String, Object> rowMap = new HashMap<String, Object>();
            for (int cellno = 0; cellno < firstRow.getLastCellNum(); cellno++) {
                Cell cell = row.getCell(cellno);
                Object value = getCellValueFormula(cell, reader.getFormulaEvaluator());
                if (value != null && !value.equals("")) {
                    emptyFlag = false;
                }
                rowMap.put(cellName[cellno], value);
            }
            if (!emptyFlag) {
                list.add(rowMap);
            }
        }
        return list;

    }

    /**
     * 根据list创建workbook
     *
     * @param workbook
     *            如需创建新文件，设为null
     * @param list
     * @param cellName
     *            列名数组
     * @param sheetName
     * @param fileType
     * @return
     */
    public static Workbook exportToWorkBook(Workbook workbook, List<HashMap<String, Object>> list, String[] cellName,
                                            String sheetName, ExcelReader.ExcelFileType fileType) {
        if (workbook == null) {
            if (ExcelReader.ExcelFileType.XLS == fileType) {
                workbook = new HSSFWorkbook();
            } else if (ExcelReader.ExcelFileType.XLSX == fileType) {
                workbook = new XSSFWorkbook();
            }else{
                workbook = new HSSFWorkbook();
            }
        }
        Sheet sheet = workbook.createSheet(sheetName);
        CellStyle cellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        // 创建表头
        Row row = sheet.createRow(0);
        for (int i = 0; i < cellName.length; i++) {
            String name = cellName[i];
            Cell cell = row.createCell(i);
            cell.setCellValue(name);
        }
        // 循环插入行
        int currentRowNum = 1;
        for (HashMap<String, Object> map : list) {
            Row currentRow = sheet.createRow(currentRowNum);
            for (int i = 0; i < cellName.length; i++) {
                String name = cellName[i];
                Cell cell = currentRow.createCell(i);
                setCellValue(cell, map.get(name), workbook, cellStyle, font);
            }
            currentRowNum++;
        }
        return workbook;

    }

    public static void setCellValue(Cell cell, Object value, Workbook wb, CellStyle cellStyle, Font cellFont) {
        CreationHelper createHelper = wb.getCreationHelper();
        if (value instanceof String) {
            if (((String) value).startsWith("HYPERLINK") || ((String) value).startsWith("=HYPERLINK")) {
                cellFont.setUnderline((byte) 1);
                cellFont.setColor(HSSFColor.BLUE.index);
                cellStyle.setFont(cellFont);
                cell.setCellStyle(cellStyle);
                cell.setCellFormula((String) value);
            }
            cell.setCellValue((String) value);
        } else if (value instanceof Date) {
            cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("YYYY/MM/DD hh:mm:ss"));
            cell.setCellValue((Date) value);
            cell.setCellStyle(cellStyle);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else if (value instanceof Integer) {
            String str = String.valueOf(value);
            cell.setCellValue(Double.parseDouble(str));
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }
    }

    public static Object getCellValue(Cell cell) {

        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return cell.getRichStringCellValue().getString().trim();
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                } else {
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    return new BigDecimal(cell.getStringCellValue());
                }
            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue();
            case Cell.CELL_TYPE_BLANK:
                return "";
            case Cell.CELL_TYPE_ERROR:
                return cell.getErrorCellValue();
            default:
                return null;
        }
    }

    public static Object getCellValueFormula(Cell cell, FormulaEvaluator formulaEvaluator) {
        if (cell == null || formulaEvaluator == null) {
            return null;
        }

        if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
            CellValue value = formulaEvaluator.evaluate(cell);
            switch(value.getCellType()){
                case Cell.CELL_TYPE_NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        return cell.getDateCellValue();
                    } else {
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        return new BigDecimal(cell.getStringCellValue());
                    }
                case Cell.CELL_TYPE_STRING:
                    return cell.getRichStringCellValue().getString().trim();
                case Cell.CELL_TYPE_BOOLEAN:
                    return cell.getBooleanCellValue();
                case Cell.CELL_TYPE_BLANK:
                    return "";
                case Cell.CELL_TYPE_ERROR:
                    return cell.getErrorCellValue();
            }
        }
        return getCellValue(cell);
    }
}

package com.baosight.cloud.tools.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by yang on 2018/5/28.
 */
public class ExcelReader {

    private Workbook workBook;

    private FormulaEvaluator formulaEvaluator;

    public ExcelReader(final InputStream excelInputStream, final ExcelFileType fileType) throws IOException {
        if (ExcelFileType.XLS == fileType) {
            workBook = new HSSFWorkbook(excelInputStream);
            formulaEvaluator = workBook.getCreationHelper().createFormulaEvaluator();
        } else if (ExcelFileType.XLSX == fileType) {
            workBook = new XSSFWorkbook(excelInputStream);
            formulaEvaluator = workBook.getCreationHelper().createFormulaEvaluator();
        } else {
            throw new IllegalArgumentException("错误的文件类型");
        }
    }

    public enum ExcelFileType {
        XLS, XLSX
    }

    public Workbook getWorkBook(){
        return this.workBook;
    }

    public FormulaEvaluator getFormulaEvaluator(){
        return this.formulaEvaluator;
    }
}

package com.baosight.cloud.tools.fs.impl.local;

import com.baosight.cloud.tools.fs.DownLoadInfo;
import com.baosight.cloud.tools.fs.FsService;
import com.baosight.cloud.tools.fs.UploadInfo;
import com.baosight.cloud.tools.fs.util.FileNameUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.*;
import java.util.Date;
import java.util.UUID;

/**
 * Created by yang on 2018/5/29.
 */
@Component
@Slf4j
public class LocalFsStorage implements FsService {

    @Autowired
    LocalFsProperties localFsProperties;

    /**
     * 上传文件
     *
     * @param uploadInfo
     * @return 实际文件存储key
     */
    @Override
    public String uploadFile(UploadInfo uploadInfo) {
        String path = getFilePath(uploadInfo);
        File dir = new File(path);
        if(!dir.exists()){
            dir.mkdirs();
        }
        String fileKey = path + getFileName(uploadInfo);
        File file = new File(fileKey);
        FileOutputStream fos = null;
        try {
            file.createNewFile();
            fos = new FileOutputStream(file);
            StreamUtils.copy(uploadInfo.getContent(),fos);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }finally {
            try{
                fos.close();
            }catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
        return fileKey;
    }

    /**
     * 下载整个文件
     *
     * @param destFileKey
     * @return
     */
    @Override
    public DownLoadInfo downLoadFile(String destFileKey) {
        File file = new File(destFileKey);
        if(file.exists()){
            FileInputStream fis = null;
            try{
                fis = new FileInputStream(file);
                byte[] content = StreamUtils.copyToByteArray(fis);
                DownLoadInfo downLoadInfo = new DownLoadInfo();
                downLoadInfo.setContent(content);
                return downLoadInfo;
            }catch(IOException e){
                log.error(e.getMessage(), e);
            }finally {
                try{
                    fis.close();
                }catch (IOException e){
                    log.error(e.getMessage(), e);
                }
            }
        }
        return null;
    }

    /**
     * 下载文件片段
     *
     * @param destFileKey
     * @param offset
     * @param length
     * @return
     */
    @Override
    public DownLoadInfo downLoadFile(String destFileKey, long offset, long length) {
        return null;
    }

    /**
     * 删除文件
     *
     * @param destFileKey
     */
    @Override
    public void deleteFile(String destFileKey) {
        File file = new File(destFileKey);
        if(file.exists()){
            file.delete();
        }
    }

    private String getFilePath(UploadInfo uploadInfo){
        String rootPath = localFsProperties.getRootPath();
        String module = uploadInfo.getFileModule();
        String dateStr = DateFormatUtils.format(new Date(), "/yyyy/MM/dd/");
        return rootPath + module + dateStr;
    }

    private String getFileName(UploadInfo uploadInfo){
        String id = UUID.randomUUID().toString();
        return id + "." + FileNameUtils.getSuffixByFilename(uploadInfo.getFileName());
    }

}

package com.baosight.cloud.tools.fs.impl.fastdfs;

import com.baosight.cloud.tools.fs.DownLoadInfo;
import com.baosight.cloud.tools.fs.FsService;
import com.baosight.cloud.tools.fs.MetaData;
import com.baosight.cloud.tools.fs.UploadInfo;
import com.baosight.cloud.tools.fs.util.FileNameUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yang on 2017/12/11.
 */
@Component
@Slf4j
public class FastDFSStorage implements FsService {

    @Autowired
    private FastDFSProperties fastDFSProperties;

    private StorageClient1 initStorageClient1() throws IOException, MyException {
        ClientGlobal.initByProperties(fastDFSProperties.toProperties());
        TrackerClient tracker = new TrackerClient();
        TrackerServer trackerServer = tracker.getConnection();
        StorageServer storageServer = null;
        StorageClient1 storageClient = new StorageClient1(trackerServer, storageServer);
        return storageClient;
    }
    /**
     * 上传文件
     *
     * @param uploadInfo
     * @return 实际文件存储key
     */
    @Override
    public String uploadFile(UploadInfo uploadInfo) {
        try {
            StorageClient1 client1 = initStorageClient1();
            String result = client1.upload_file1( uploadInfo.getContent(), FileNameUtils.getSuffixByFilename(uploadInfo.getFileName()),getMetaInfo(uploadInfo));
            return result;
        } catch (IOException | MyException e) {
            log.error(e.getMessage());
            return "";
        }
    }

    /**
     * 下载整个文件
     *
     * @param destFileKey
     * @return
     */
    @Override
    public DownLoadInfo downLoadFile(String destFileKey) {
        try {
            StorageClient1 client1 = initStorageClient1();
            byte[] result = client1.download_file1(destFileKey);
            NameValuePair[] metadata = client1.get_metadata1(destFileKey);
            DownLoadInfo downLoadInfo = new DownLoadInfo();
            downLoadInfo.setContent(result);
            downLoadInfo.setFileInfo(convertNameValuePair(metadata));
            return downLoadInfo;
        } catch (IOException | MyException e) {
            log.error(e.getMessage());
            return null;
        }
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
        try {
            StorageClient1 client1 = initStorageClient1();
            byte[] result = client1.download_file1(destFileKey, offset, length);
            NameValuePair[] metadata = client1.get_metadata1(destFileKey);
            DownLoadInfo downLoadInfo = new DownLoadInfo();
            downLoadInfo.setFileInfo(convertNameValuePair(metadata));
            downLoadInfo.setContent(result);
            return downLoadInfo;
        } catch (IOException | MyException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * 删除文件
     *
     * @param destFileKey
     */
    @Override
    public void deleteFile(String destFileKey) {
        try {
            StorageClient1 client1 = initStorageClient1();
            client1.delete_file1(destFileKey);
        } catch (IOException | MyException e) {
            log.error(e.getMessage());
        }

    }

    private NameValuePair[] getMetaInfo(UploadInfo uploadInfo){
        NameValuePair[] meta_list = new NameValuePair[3];
        meta_list[0] = new NameValuePair("fileSize", ObjectUtils.getDisplayString(uploadInfo.getFileSize()));
        meta_list[1] = new NameValuePair("fileName", uploadInfo.getFileName());
        meta_list[2] = new NameValuePair("uploadTime", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        return meta_list;
    }

    private Set<MetaData> convertNameValuePair(NameValuePair[] nameValuePairs){
        Set<MetaData> metaDataSet = new HashSet<>();
        for(NameValuePair nameValuePair: nameValuePairs){
            MetaData tmp = new MetaData();
            tmp.setName(nameValuePair.getName());
            tmp.setValue(nameValuePair.getValue());
            metaDataSet.add(tmp);
        }
        return metaDataSet;
    }
}

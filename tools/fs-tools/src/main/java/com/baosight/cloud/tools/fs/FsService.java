package com.baosight.cloud.tools.fs;

/**
 * 存储
 * Created by yang on 2017/12/11.
 */
public interface FsService {

    /**
     * 上传文件
     * @param uploadInfo
     * @return 实际文件存储key
     */
    String uploadFile(UploadInfo uploadInfo);

    /**
     * 下载整个文件
     * @param destFileKey
     * @return
     */
    DownLoadInfo downLoadFile(String destFileKey);

    /**
     * 下载文件片段
     * @param destFileKey
     * @param offset
     * @param length
     * @return
     */
    DownLoadInfo downLoadFile(String destFileKey, long offset, long length);

    /**
     * 删除文件
     * @param destFileKey
     */
    void deleteFile(String destFileKey);
}

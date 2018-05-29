package com.baosight.cloud.tools.fs;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by yang on 2017/12/11.
 */
@Data
public class UploadInfo implements Serializable {

    private static final long serialVersionUID = 925001639326304815L;

    byte[] content;

    String fileName;

    long fileSize;

    String MIMEType;

    String fileModule;

    String destFileKey;
}

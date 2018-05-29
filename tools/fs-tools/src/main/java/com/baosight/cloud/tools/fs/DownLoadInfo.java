package com.baosight.cloud.tools.fs;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by yang on 2017/12/11.
 */
@Data
public class DownLoadInfo implements Serializable {

    private static final long serialVersionUID = 6952250403701540387L;

    byte[] content;

    Set<MetaData> fileInfo;
}

package com.baosight.cloud.tools.fs.util;

/**
 * Created by yang on 2017/12/11.
 */
public class FileNameUtils {
    /**
     * 获取后缀名（不包含.）
     * @param filename
     * @return
     */
    public static String getSuffixByFilename(String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
    }

    /**
     * 获取MIMEType
     * @param filename
     * @return
     */
    public static String getMIMEType(String filename){
        return MIMEType.getMIMEType(getSuffixByFilename(filename));
    }
}

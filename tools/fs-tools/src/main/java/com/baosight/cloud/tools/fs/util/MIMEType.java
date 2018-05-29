package com.baosight.cloud.tools.fs.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yang on 2017/12/11.
 */
public class MIMEType {
    public static final Map<String, String> types = new HashMap<String, String>() {
        private static final long serialVersionUID = 1L;

        {
            put("atom", "application/atom+xml");
            put("woff", "application/font-woff");
            put("jar", "application/java-archive");
            put("war", "application/java-archive");
            put("ear", "application/java-archive");
            put("js", "application/javascript");
            put("json", "application/json");
            put("hqx", "application/mac-binhex40");
            put("doc", "application/msword");
            put("pdf", "application/pdf");
            put("ps", "application/postscript");
            put("eps", "application/postscript");
            put("ai", "application/postscript");
            put("rss", "application/rss+xml");
            put("rtf", "application/rtf");
            put("m3u8", "application/vnd.apple.mpegurl");
            put("kml", "application/vnd.google-earth.kml+xml");
            put("kmz", "application/vnd.google-earth.kmz");
            put("xls", "application/vnd.ms-excel");
            put("eot", "application/vnd.ms-fontobject");
            put("ppt", "application/vnd.ms-powerpoint");
            put("pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation");
            put("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            put("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            put("wmlc", "application/vnd.wap.wmlc");
            put("7z", "application/x-7z-compressed");
            put("cco", "application/x-cocoa");
            put("xhtml", "application/xhtml+xml");
            put("jardiff", "application/x-java-archive-diff");
            put("jnlp", "application/x-java-jnlp-file");
            put("run", "application/x-makeself");
            put("pl", "application/x-perl");
            put("pm", "application/x-perl");
            put("prc", "application/x-pilot");
            put("pdb", "application/x-pilot");
            put("rar", "application/x-rar-compressed");
            put("rpm", "application/x-redhat-package-manager");
            put("sea", "application/x-sea");
            put("swf", "application/x-shockwave-flash");
            put("xspf", "application/xspf+xml");
            put("sit", "application/x-stuffit");
            put("tcl", "application/x-tcl");
            put("tk", "application/x-tcl");
            put("der", "application/x-x509-ca-cert");
            put("pem", "application/x-x509-ca-cert");
            put("crt", "application/x-x509-ca-cert");
            put("xpi", "application/x-xpinstall");
            put("zip", "application/zip");
            put("mid", "audio/midi");
            put("midi", "audio/midi");
            put("kar", "audio/midi");
            put("mp3", "audio/mpeg");
            put("ogg", "audio/ogg");
            put("m4a", "audio/x-m4a");
            put("ra", "audio/x-realaudio");
            put("gif", "image/gif");
            put("jpeg", "image/jpeg");
            put("jpg", "image/jpeg");
            put("png", "image/png");
            put("svg", "image/svg+xml");
            put("svgz", "image/svg+xml");
            put("tif", "image/tiff");
            put("tiff", "image/tiff");
            put("wbmp", "image/vnd.wap.wbmp");
            put("webp", "image/webp");
            put("ico", "image/x-icon");
            put("jng", "image/x-jng");
            put("bmp", "image/x-ms-bmp");
            put("css", "text/css");
            put("html", "text/html");
            put("shtml", "text/html");
            put("htm", "text/html   ");
            put("mml", "text/mathml");
            put("txt", "text/plain");
            put("jad", "text/vnd.sun.j2me.app-descriptor");
            put("wml", "text/vnd.wap.wml");
            put("htc", "text/x-component");
            put("xml", "text/xml");
            put("3gpp", "video/3gpp");
            put("3gp", "video/3gpp");
            put("ts", "video/mp2t");
            put("mp4", "video/mp4");
            put("mpeg", "video/mpeg");
            put("mpg", "video/mpeg");
            put("mov", "video/quicktime");
            put("webm", "video/webm");
            put("flv", "video/x-flv");
            put("m4v", "video/x-m4v");
            put("mng", "video/x-mng");
            put("asx", "video/x-ms-asf");
            put("asf", "video/x-ms-asf");
            put("avi", "video/x-msvideo");
            put("wmv", "video/x-ms-wmv");

        }
    };

    public static String getMIMEType(String suffix) {
        return (String) MIMEType.types.get(suffix);
    }
}

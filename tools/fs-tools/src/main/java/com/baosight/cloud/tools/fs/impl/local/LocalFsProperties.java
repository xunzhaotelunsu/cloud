package com.baosight.cloud.tools.fs.impl.local;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by yang on 2018/5/29.
 */
@ConfigurationProperties(prefix = "localfs")
@Data
public class LocalFsProperties {

    String rootPath = "/files/";
}

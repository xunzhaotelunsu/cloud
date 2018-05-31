package com.baosight.cloud.tools.fs.impl.local;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yang on 2018/5/30.
 */
@Configuration
@EnableConfigurationProperties(value = {LocalFsProperties.class})
public class LocalFsConfig {

    @Bean
    public LocalFsStorage getLocalFsStorage(){
        return new LocalFsStorage();
    }
}

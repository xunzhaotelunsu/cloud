package com.baosight.cloud.tools.fs.impl.fastdfs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yang on 2017/12/11.
 */
@Configuration
@EnableConfigurationProperties(value = {FastDFSProperties.class})
@Slf4j
public class FastDFSConfig {

    @Bean
    public FastDFSStorage getFastDFSStorage(){
        return new FastDFSStorage();
    }

}

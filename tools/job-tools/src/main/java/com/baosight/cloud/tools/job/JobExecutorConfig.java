package com.baosight.cloud.tools.job;

import com.xxl.job.core.executor.XxlJobExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yang on 2017/6/1.
 */
@Configuration
@EnableConfigurationProperties(JobExecutorProperties.class)
public class JobExecutorConfig {

    @Autowired
    private JobExecutorProperties jobExecutorProperties;

    @Bean(initMethod = "start",destroyMethod = "destroy")
    public XxlJobExecutor getJobExecutor(){
        XxlJobExecutor executor = new XxlJobExecutor();
        executor.setPort(jobExecutorProperties.getPort());
        executor.setAppName(jobExecutorProperties.getAppName());
        executor.setAdminAddresses(jobExecutorProperties.getAdminAddress());
        executor.setLogPath(jobExecutorProperties.getLogPath());
        return executor;
    }
}

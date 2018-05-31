package com.baosight.cloud.tools.job;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by yang on 2017/6/1.
 */
@Data
@ConfigurationProperties(prefix = "job.executor")
public class JobExecutorProperties {

    int port = 9999;

    String appName;

    String adminAddress;

    String logPath = "/apps/logs/job/jobHandler/";
}

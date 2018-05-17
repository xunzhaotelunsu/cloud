package com.baosight.cloud.service.activiti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by yang on 2018/5/16.
 */
@SpringBootApplication
@EnableEurekaClient
@EnableTransactionManagement
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class,
        org.activiti.spring.boot.SecurityAutoConfiguration.class,
        ManagementWebSecurityAutoConfiguration.class})
public class ActivitiApplication {

    public static void main(String[] args){
        SpringApplication.run(ActivitiApplication.class, args);
    }
}

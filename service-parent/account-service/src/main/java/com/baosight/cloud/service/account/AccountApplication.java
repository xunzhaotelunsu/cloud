package com.baosight.cloud.service.account;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by yang on 2018/3/20.
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = "com.baosight.cloud.service.account.persist.mapper")
@EnableTransactionManagement
public class AccountApplication {

    public static void main(String[] args){
        SpringApplication.run(AccountApplication.class, args);
    }
}

package com.baosight.cloud.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * Created by yang on 2018/3/16.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class OAuth2Application {

    public static void main(String[] args){
        SpringApplication.run(OAuth2Application.class, args);
    }

}

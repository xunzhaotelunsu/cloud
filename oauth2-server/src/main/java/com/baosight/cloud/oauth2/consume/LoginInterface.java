package com.baosight.cloud.oauth2.consume;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by yang on 2018/3/21.
 */
@FeignClient(value = "account-service")
public interface LoginInterface {

    @PostMapping(value = "/login/tryLogin")
    Map<String, Object> tryLogin(@RequestParam("loginName") String loginName,
                                 @RequestParam("password") String password);

}


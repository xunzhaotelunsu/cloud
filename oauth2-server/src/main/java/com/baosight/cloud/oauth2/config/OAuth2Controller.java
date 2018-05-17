package com.baosight.cloud.oauth2.config;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by yang on 2018/3/16.
 */
@RestController
public class OAuth2Controller {

    @RequestMapping("/user")
    Object user(Principal principal) {

        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) principal;

        return oAuth2Authentication.getUserAuthentication().getPrincipal();

    }
}

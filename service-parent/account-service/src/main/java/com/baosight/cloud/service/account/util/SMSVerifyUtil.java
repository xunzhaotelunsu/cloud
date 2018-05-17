package com.baosight.cloud.service.account.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by yang on 2018/3/22.
 */
@Component
public class SMSVerifyUtil {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    private static final String KEY_PREFIX = "SMSVerify:";

    public String sendVerifyCode(String mobile, String bizType){
        String verifyCode = generateVerifyCode();
        String key = KEY_PREFIX + bizType + ":" + mobile;
        stringRedisTemplate.opsForValue().set(key, verifyCode, 600L, TimeUnit.SECONDS);
        //todo sendSMS
        return verifyCode;
    }

    public boolean checkVerifyCode(String mobile, String bizType, String inputVerifyCode){
        String key = KEY_PREFIX + bizType + ":" + mobile;
        if(!stringRedisTemplate.hasKey(key)){
            return false;
        }
        String value = stringRedisTemplate.opsForValue().get(key);
        if (inputVerifyCode.equals(value)) {
            stringRedisTemplate.delete(key);
            return true;
        }else{
            stringRedisTemplate.delete(key);
            return false;
        }
    }

    /**
     * 生成6位验证码
     * @return
     */
    private String generateVerifyCode(){
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<6;i++){
            Random r = new Random();
            int mod = r.nextInt(10);
            builder.append(mod);
        }
        return builder.toString();
    }
}

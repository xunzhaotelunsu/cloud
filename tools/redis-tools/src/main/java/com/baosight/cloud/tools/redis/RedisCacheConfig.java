package com.baosight.cloud.tools.redis;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.ObjectUtils;

/**
 * Created by yang on 2018/5/28.
 */
@Configuration
@EnableCaching
@ConditionalOnBean(StringRedisTemplate.class)
public class RedisCacheConfig extends CachingConfigurerSupport {

    @Bean
    public KeyGenerator wiselyKeyGenerator(){
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(ObjectUtils.getDisplayString(obj));
            }
            return sb.toString();
        };
    }

    @Bean(name = "redisCacheManager")
    public CacheManager cacheManager(@SuppressWarnings("SpringJavaAutowiringInspection")StringRedisTemplate redisTemplate) {
        RedisCacheManager cm =  new RedisCacheManager(redisTemplate);
        //默认缓存600秒
        cm.setDefaultExpiration(600L);
        return cm;
    }

    @Bean
    public StringRedisOperation getRedisCacheOperation() {
        return new StringRedisOperation();
    }

}

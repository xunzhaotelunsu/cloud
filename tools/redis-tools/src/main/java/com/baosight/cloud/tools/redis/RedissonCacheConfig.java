package com.baosight.cloud.tools.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yang on 2018/5/28.
 */
@Configuration
@EnableCaching
@ConditionalOnBean(RedisProperties.class)
public class RedissonCacheConfig {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    RedisProperties redisProperties;

    @Bean(destroyMethod = "shutdown")
    public RedissonClient getClient() {
        Config config = new Config();
        if(!ObjectUtils.isEmpty(redisProperties.getCluster())&&!CollectionUtils.isEmpty(redisProperties.getCluster().getNodes())){
            //cluster模式
            List<String> clusterNodes = redisProperties.getCluster().getNodes();
            String[] nodes = StringUtils.toStringArray(clusterNodes);
            config.useClusterServers()
                    .addNodeAddress(nodes)
                    .setScanInterval(20000)
                    .setMasterConnectionPoolSize(10000)
                    .setSlaveConnectionPoolSize(10000)
                    .setPassword(redisProperties.getPassword());
        }else{
            //singleServer模式
            config.useSingleServer()
                    .setAddress(redisProperties.getHost() + ":" + redisProperties.getPort())
                    .setPassword(redisProperties.getPassword())
                    .setDatabase(redisProperties.getDatabase())
                    .setConnectionMinimumIdleSize(redisProperties.getPool().getMinIdle())
                    .setConnectionPoolSize(redisProperties.getPool().getMaxActive())
                    .setTimeout(redisProperties.getTimeout());
        }
        return Redisson.create(config);
    }

    @Bean(name = "redissonCacheManager")
    public CacheManager cacheManager() {
        Map<String, CacheConfig> config = new HashMap<String, CacheConfig>();
        config.put("redissonCache", new CacheConfig(10*60*1000, 15*60*1000));
        return new RedissonSpringCacheManager(getClient(), config);
    }

}

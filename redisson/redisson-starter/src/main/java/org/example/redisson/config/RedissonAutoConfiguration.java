package org.example.redisson.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: zyh
 * @date: 2022/3/4
 */
@ConditionalOnClass(Redisson.class)
@EnableConfigurationProperties(RedissonAutoConfiguration.class)
@Configuration
public class RedissonAutoConfiguration {

    @Bean
    public RedissonClient redissonClient(RedissonProperties redissonProperties){
        Config config = new Config();
        String prefix = "redis://";
        if(redissonProperties.isSsl()){
            prefix = "rediss://";
        }
        // 程序化配置方法
        // config.setTransportMode(TransportMode.EPOLL);

        // 单节点连接配置
        config.useSingleServer()
                .setAddress(prefix+redissonProperties.getHost()+":"+redissonProperties.getPort())
                .setConnectTimeout(redissonProperties.getTimeout());
        return Redisson.create(config);
    }

}

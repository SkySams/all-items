package org.example.redisson.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: zyh
 * @date: 2022/3/4
 */

@Data
@ConfigurationProperties(prefix = "bobo.redisson")
public class RedissonProperties {

    private String host = "localhost";

    private int port = 6379;

    private int timeout = 0;

    private boolean ssl = false;

}

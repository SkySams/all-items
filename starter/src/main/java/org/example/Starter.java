package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author: zyh
 * @date: 2022/3/4
 */
@EnableCaching
@EnableAsync // 开启异步请求
@SpringBootApplication
@ConfigurationPropertiesScan(basePackages="org.example.**")
public class Starter {

    /**
     * spring boot 案例 https://github.com/spring-projects/spring-boot/tree/v2.0.0.M3/spring-boot-samples
     * @param args
     */

    public static void main(String[] args) {
        SpringApplication.run(Starter.class, args);
    }

}

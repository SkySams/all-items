package org.apche;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: zyh
 * @date: 2023/3/28
 */
@SpringBootApplication
@EnableDubbo
public class ProviderApp {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ProviderApp.class, args);
    }
}



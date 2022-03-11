package org.example;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: zyh
 * @date: 2022/3/9
 */
@EnableDubbo(scanBasePackages = "org.example")
@SpringBootApplication
public class Custom {

    public static void main(String[] args) {
        SpringApplication.run(Custom.class,args);
    }

}

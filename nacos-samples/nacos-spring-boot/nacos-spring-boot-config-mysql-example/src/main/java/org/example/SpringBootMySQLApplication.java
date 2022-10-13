package org.example;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@NacosPropertySource(dataId = "mysql.yml")
public class SpringBootMySQLApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMySQLApplication.class, args);
    }
}
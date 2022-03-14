package org.example;


import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("org.example.dao")
@EnableDubbo(scanBasePackages = "org.example")
@SpringBootApplication
public class Stock {

    public static void main(String[] args) {
        SpringApplication.run(Stock.class, args);
    }

}

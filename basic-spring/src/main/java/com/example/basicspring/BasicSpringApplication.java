package com.example.basicspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages="org.example.**")
public class BasicSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicSpringApplication.class, args);
    }

}

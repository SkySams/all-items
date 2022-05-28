package org.example;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * @author: zyh
 * @date: 2022/3/4
 */
@SpringBootApplication
@ConfigurationPropertiesScan(basePackages="org.example.**")
public class Starter {

    public static void main(String[] args) {
        SpringApplication.run(Starter.class, args);

    }

}

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

    /**
     * spring boot 案例 https://github.com/spring-projects/spring-boot/tree/v2.0.0.M3/spring-boot-samples
     * @param args
     */

    public static void main(String[] args) {
        SpringApplication.run(Starter.class, args);

    }

}

package org.example.futureitem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
public class FutureItemApplication {

    public static void main(String[] args)  {
//        SpringApplication.run(FutureItemApplication.class, args);
        ConfigurableApplicationContext applicationContext = SpringApplication.run(FutureItemApplication.class, args);
        ConfigurableEnvironment configurableEnvironment = applicationContext.getEnvironment();
        String userName = configurableEnvironment.getProperty("user.name");
        String userAge  = configurableEnvironment.getProperty("user.age");
        //Get the current deployment environment
        String currentEnv = applicationContext.getEnvironment().getProperty("current.env");
        System.err.println("in "+currentEnv+" enviroment; "+"user name :" + userName + "; age: " + userAge);
    }

}

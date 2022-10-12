package org.apache.dubbo.springboot.demo.consumer;

/**
 * @author: zyh
 * @date: 2022/10/12
 */

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.dubbo.springboot.demo.DemoService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @author: zyh
 * @date: 2022/10/12
 */
@SpringBootApplication
@Service
@EnableDubbo
public class ConsumerApplication {

    @DubboReference
    private DemoService demoService;

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(ConsumerApplication.class, args);
        ConsumerApplication application = context.getBean(ConsumerApplication.class);
        String result = application.doSayHello("world");
        System.out.println("result: " + result);

        String result1 = application.doSayHello("world1");
        System.out.println("result: " + result1);
    }

    public String doSayHello(String name) {
        return demoService.sayHello(name);
    }
}

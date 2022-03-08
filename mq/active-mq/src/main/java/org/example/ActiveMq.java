package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

/**
 * @EnableJms 启动消息队列
 * @author: zyh
 * @date: 2022/3/8
 */
@EnableJms
@SpringBootApplication
public class ActiveMq {

    public static void main(String[] args) {
        SpringApplication.run(ActiveMq.class,args);
    }

}

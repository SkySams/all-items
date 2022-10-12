package org.example;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

/**
 *
 * ActiveMQ的消息持久化机制有JDBC，AMQ，KahaDB和LevelDB
 *
 * @EnableJms 启动消息队列
 * @author: zyh
 * @date: 2022/3/8
 */
@EnableJms
@SpringBootApplication
public class ActiveMq {

    public static void main(String[] args) {
        long s = 100;
        Integer promotionPersonNum = Math.toIntExact(s);
        System.out.println(promotionPersonNum);

        SpringApplication.run(ActiveMq.class,args);
    }
//        public static void main(String[] args) {
//            SpringApplication application = new SpringApplication(ActiveMq.class);
//            application.setBannerMode(Banner.Mode.OFF);
//            application.run(args);
//        }

}

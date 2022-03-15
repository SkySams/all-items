package org.example.order;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: zyh
 * @date: 2022/3/10
 */
@MapperScan("org.example.order.dao")
@EnableDubbo(scanBasePackages = "org.example")
@SpringBootApplication(exclude = {
//        com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure.class
})
public class Order {

    public static void main(String[] args) {
        SpringApplication.run(Order.class,args);
    }
}

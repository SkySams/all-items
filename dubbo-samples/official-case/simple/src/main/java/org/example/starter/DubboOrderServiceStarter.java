package org.example.starter;

import org.example.ApplicationKeeper;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: zyh
 * @date: 2023/3/10
 */
public class DubboOrderServiceStarter {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext orderContext = new ClassPathXmlApplicationContext(new String[]{"spring/dubbo-order-service.xml"});
        orderContext.getBean("service");
        new ApplicationKeeper(orderContext).keep();
    }

}

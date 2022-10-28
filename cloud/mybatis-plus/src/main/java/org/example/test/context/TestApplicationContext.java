package org.example.test.context;

import org.example.entity.UserLog;
import org.example.entity.eo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: zyh
 * @date: 2022/10/28
 */
public class TestApplicationContext {


    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/xml/service.xml");
        System.out.println("applicationName: "+ applicationContext.getApplicationName());
        UserLog userLog =  applicationContext.getBean("userLog", UserLog.class);
        System.out.println(userLog.getUserId());
        System.out.println(applicationContext.getBean("userLog"));

        User user = applicationContext.getBean("user", User.class);
        System.out.println("user: "+user.getAge());
    }

}

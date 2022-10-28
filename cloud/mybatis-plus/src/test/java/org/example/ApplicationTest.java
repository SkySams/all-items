package org.example;

import org.example.entity.UserLog;
import org.example.service.UserLogService;
import org.example.service.impl.UserLogServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: zyh
 * @date: 2022/10/28
 */
@SpringBootTest(classes = MybatisPlusApp.class)
public class ApplicationTest {


    @Test
    public void application() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/xml/service.xml");

        UserLogService userLogService = applicationContext.getBean("userLogServiceImpl", UserLogServiceImpl.class);
        UserLog userLog = userLogService.queryById(1);
        System.out.println(userLog);

    }

}

package org.example.aop.shoping;

import org.example.aop.shoping.org.example.aop.AppConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author: zyh
 * @date: 2022/3/5
 */
public class AopTest {

    @Test
    public  void main() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Girl girl = (Girl) context.getBean("girl");
        girl.buy(900);
    }


}

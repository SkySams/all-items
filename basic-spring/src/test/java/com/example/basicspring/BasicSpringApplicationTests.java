package com.example.basicspring;

import com.example.basicspring.service.PetStoreService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

@SpringBootTest
class BasicSpringApplicationTests {


    // 前缀
    private final String PREFIX_PATH = "/xml/";


    @Test
    void contextLoads() {
        ApplicationContext context = new ClassPathXmlApplicationContext(PREFIX_PATH+"services.xml");
        PetStoreService service = context.getBean("petStoreService", PetStoreService.class);
        service.display();
        System.out.println(context.getDisplayName());
        System.out.println(context.getBean("petStoreService"));
    }


    @Test
    void generic(){
        GenericApplicationContext context = new GenericApplicationContext();
        new XmlBeanDefinitionReader(context).loadBeanDefinitions(PREFIX_PATH+"services.xml",PREFIX_PATH+"daos.xml");
        context.refresh();
    }

}

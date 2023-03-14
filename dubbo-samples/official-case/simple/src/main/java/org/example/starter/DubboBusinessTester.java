package org.example.starter;

import org.example.service.BusinessService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: zyh
 * @date: 2023/3/14
 */
public class DubboBusinessTester {

    public static void main(String[] args) {
        /**
         *  4. The whole e-commerce platform is ready , The buyer(U100001) create an order on the sku(C00321) , the
         *  count is 2
         */
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring/dubbo-business.xml"});
        final BusinessService business = (BusinessService)context.getBean("business");
        business.purchase("U100001", "C00321", 2);
    }

}

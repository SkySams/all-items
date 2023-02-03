package com.example.basicspring.service.proxy;

import com.example.basicspring.service.OrderService;
import com.example.basicspring.service.impl.OrderServiceImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: zyh
 * @date: 2023/2/3
 */
public class ProxyMain {

    public static void main(String[] args) {
        new ProxyMain().cglib();
    }


    public void cglib(){
        // ps：cglib动态代理不能代理声明为final类型的类和方法
        CglibLogProxy  cglibLogProxy=new CglibLogProxy();
        OrderServiceImpl   orderProxy=   cglibLogProxy.getObjByEnhancer(OrderServiceImpl.class);
        String userName="JCccc";
        Map<String,Integer> orderMap=new HashMap();
        orderMap.put("白米饭",2);
        orderMap.put("红烧肉",1);
        orderMap.put("水煮鱼",1);
        orderMap.put("番茄炒蛋",1);
        Map map = orderProxy.executeFoodOrder(userName, orderMap);
        System.out.println(map.toString());

    }

    public void dynami() {
        //动态代理调用方式
        OrderService dynamicsOrderProxy = (OrderService) new DynamicsLogProxy().bind(new OrderServiceImpl());
        String userName = "JCccc";
        Map<String, Integer> orderMap = new HashMap<>();
        orderMap.put("白米饭", 2);
        orderMap.put("红烧肉", 1);
        orderMap.put("水煮鱼", 1);
        orderMap.put("番茄炒蛋", 1);
        //通过动态代理类去调用不同方法
        Map resultMap = dynamicsOrderProxy.executeFoodOrder(userName, orderMap);

        System.out.println("动态代理方法执行完毕,结果：" + resultMap);

    }

    public void staticProxy() {
        //静态代理调用方式
        OrderServiceImpl orderService = new OrderServiceImpl();
        OrderServiceLogProxy orderServiceLogProxy = new OrderServiceLogProxy(orderService);
        String userName = "JCccc";
        Map<String, Integer> orderMap = new HashMap<>();
        orderMap.put("白米饭", 2);
        orderMap.put("红烧肉", 1);
        orderMap.put("水煮鱼", 1);
        orderMap.put("番茄炒蛋", 1);
        Map resultMap = orderServiceLogProxy.executeFoodOrder(userName, orderMap);

        System.out.println("静态代理方法执行完毕,结果：" + resultMap);
    }

}

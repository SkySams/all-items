package com.example.basicspring.service.proxy;

import com.example.basicspring.service.OrderService;
import com.example.basicspring.service.impl.OrderServiceImpl;

import java.util.Map;

/**
 * @author: zyh
 * @date: 2023/2/3
 */
public class OrderServiceLogProxy implements OrderService {

    private OrderServiceImpl orderService;

    public OrderServiceLogProxy(OrderServiceImpl orderServiceImpl) {
        this.orderService = orderServiceImpl;
    }

    @Override
    public Map executeFoodOrder(String userName, Map<String, Integer> foodMap) {
        this.before(userName,foodMap);
        Map map = orderService.executeFoodOrder(userName, foodMap);
        this.after(userName,foodMap);
        return map;

    }

    public void before(String userName, Map<String, Integer> foodMap){
        System.out.println("+静态代理LogProxy");
        System.out.println("+下单前我们做点什么.");
        System.out.println("+正在获取用户："+userName+"用户信息......");
        System.out.println("+正在记录用户："+userName+"选择的菜品："+foodMap.toString());
        System.out.println("+准备执行主业务");
    }
    public void after(String userName, Map<String, Integer> foodMap){
        System.out.println("+下单后我们做点什么.");
        System.out.println("+记录用户"+userName+"下单后的详情信息:"+foodMap.toString());
    }

}

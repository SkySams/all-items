package com.example.basicspring.context;

import com.example.basicspring.entity.dto.OrderInfo;
import com.example.basicspring.service.OrderStrategyService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: zyh
 * @date: 2023/2/6
 * 利用Spring的发现机制,将实现了OrderStrategyService的类都put到orderStrategyMap里面。
 * 后面只需要根据platformId对应好 各个实现类的注解 如： @Component("Domestic") 就可以取出不同的业务实现类
 */
@Service
public class OrderStrategyContext {

    private final Map<String, OrderStrategyService> orderStrategyMap = new ConcurrentHashMap<>();

    public OrderStrategyContext(Map<String, OrderStrategyService> strategyMap) {
        this.orderStrategyMap.clear();
        strategyMap.forEach((k,v)-> this.orderStrategyMap.put(k,v));
    }

    /**
     * ps： 可以看到getResource这个方法，获取资源，里面其实就是根据订单类型去取出对应的业务实现类。
     * @param orderInfo
     * @return
     */
    public OrderStrategyService getResource(OrderInfo orderInfo){
        return orderStrategyMap.get(orderInfo.getPlatFormType());
    }


}

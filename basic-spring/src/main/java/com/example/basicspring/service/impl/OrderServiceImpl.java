package com.example.basicspring.service.impl;

import com.example.basicspring.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: zyh
 * @date: 2023/2/3
 */
@Service
public class OrderServiceImpl implements OrderService {
    /**
     * 下单
     *
     * @param userName
     * @param foodMap
     */
    @Override
    public Map executeFoodOrder(String userName, Map<String, Integer> foodMap) {
        System.out.println("*********【下单主业务】*********");
        Map resultMap=new HashMap();

        for (Map.Entry<String,Integer> m : foodMap.entrySet()){
            String foodName = m.getKey();
            Integer value = m.getValue();
            //模拟一些杂七杂八的业务
            System.out.println("【下单主业务】"+foodName+"---模拟这个菜的一些杂七杂八的业务");
            resultMap.put(foodName,"ok");
        }
        return resultMap;
    }
}

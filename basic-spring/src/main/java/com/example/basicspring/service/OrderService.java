package com.example.basicspring.service;

import java.util.Map;

/**
 * @author: zyh
 * @date: 2023/2/3
 */
public interface  OrderService {

    /**
     *   下单
     */
    Map executeFoodOrder(String userName, Map<String, Integer> foodMap);
}

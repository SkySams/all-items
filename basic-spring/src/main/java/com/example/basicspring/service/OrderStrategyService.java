package com.example.basicspring.service;

import com.example.basicspring.entity.dto.OrderInfo;

/**
 * springboot 策略模式
 * @author: zyh
 * @date: 2023/2/6
 */
public interface OrderStrategyService {

    /**
     * 预下单
     * @param orderInfo
     * @return
     */
    String  preCreateOrder(OrderInfo orderInfo);

}

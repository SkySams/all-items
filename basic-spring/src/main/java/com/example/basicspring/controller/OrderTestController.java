package com.example.basicspring.controller;

import com.example.basicspring.context.OrderStrategyContext;
import com.example.basicspring.entity.dto.OrderInfo;
import com.example.basicspring.service.OrderStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * spring boot 策略模式
 * @author: zyh
 * @date: 2023/2/6
 */
@RestController
public class OrderTestController {

    @Autowired
    private OrderStrategyContext orderStrategyContext;

    @PostMapping("/testStrategy")
    public String testStrategy(@RequestBody OrderInfo orderInfo){
        OrderStrategyService orderServiceImpl = orderStrategyContext.getResource(orderInfo);
        String resultTest = orderServiceImpl.preCreateOrder(orderInfo);
        return resultTest;
    }

}

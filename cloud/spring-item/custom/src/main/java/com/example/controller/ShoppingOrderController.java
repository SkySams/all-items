package com.example.controller;

import com.example.service.ShoppingOrderService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zyh
 * @date: 2022/3/10
 */
@RestController
public class ShoppingOrderController {

    @DubboReference(loadbalance = "roundrobin",timeout = 10000)
    ShoppingOrderService shoppingOrderService;

    @GetMapping("order")
    public String order(){
        return shoppingOrderService.add();
    }

}

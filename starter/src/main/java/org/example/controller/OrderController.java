package org.example.controller;

import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Qualifier("CustomServiceImpl")
    @Autowired
    private OrderService orderService;

    @GetMapping("order")
    public void one(){
        orderService.play();
    }

}

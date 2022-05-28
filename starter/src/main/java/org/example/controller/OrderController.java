package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "ORDER")
@RestController
public class OrderController {

    @Qualifier("CustomServiceImpl")
    @Autowired
    private OrderService orderService;

    @ApiOperation("ONE")
    @GetMapping("order")
    public String one(@RequestParam String name){
        orderService.play();

        return "success";
    }

}

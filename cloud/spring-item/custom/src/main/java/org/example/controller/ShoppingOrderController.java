package org.example.controller;

import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.config.annotation.DubboReference;
import org.example.service.ShoppingOrderService;
import org.example.service.StockSevice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zyh
 * @date: 2022/3/10
 */
@RestController
public class ShoppingOrderController {

    @DubboReference(loadbalance = "roundrobin",check = false)
    private ShoppingOrderService shoppingOrderService;

    @DubboReference(check = false)
    private StockSevice stockSevice;

    @GetMapping("order")
    public String order(){
        return shoppingOrderService.add();
    }

    @GetMapping("stock")
    public String stock (){
        return stockSevice.stock();
    }

    @GetMapping("create")
    @GlobalTransactional(rollbackFor = Exception.class)

    public String createOrder(){
        // 创建订单
        shoppingOrderService.createOrder();
        //减库存
        stockSevice.reduceStock();
        return "success";
    }

}

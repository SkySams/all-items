package com.example.order.service.impl;

import com.example.service.ShoppingOrderService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author: zyh
 * @date: 2022/3/10
 */
@DubboService
public class ShoppingOrderServiceImpl implements ShoppingOrderService {
    @Override
    public String add() {
        return "nice";
    }
}

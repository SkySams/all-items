package org.example.service.impl;

import org.springframework.stereotype.Service;

@Service("OrderServiceImpl")
public class OrderServiceImpl extends AbstractOrderService {

    @Override
    public void add() {
        System.out.println("OrderService");
    }

    @Override
    public void play() {
        super.play();
        System.out.println("OrderService play");
    }
}

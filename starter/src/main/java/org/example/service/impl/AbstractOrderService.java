package org.example.service.impl;

import org.example.service.OrderService;

public abstract class AbstractOrderService implements OrderService {

    @Override
    public abstract void add();

    @Override
    public void play() {
        System.out.println("abstractOrderService play");
    }
}

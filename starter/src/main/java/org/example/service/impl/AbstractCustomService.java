package org.example.service.impl;

import org.example.service.OrderService;

public abstract   class AbstractCustomService implements OrderService {


    @Override
    public void add() {
        System.out.println("一定");
    }

    @Override
    public void play() {
        System.out.println("nice");
    }
}

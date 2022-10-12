package org.example.pattern.duck.action.impl;

import org.example.pattern.duck.action.Animal;

/**
 * @author: zyh
 * @date: 2022/9/30
 */
public abstract class AbstractAnimal implements Animal {
    /**
     * 行走
     */
    @Override
    public void walk() {
        System.out.println("所有动物都是行走");
    }

    @Override
    public void play(){
        System.out.println("行走速度不知道");
    }
}

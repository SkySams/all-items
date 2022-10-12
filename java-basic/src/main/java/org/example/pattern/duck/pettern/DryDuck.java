package org.example.pattern.duck.pettern;

import org.example.pattern.duck.action.Action;
import org.example.pattern.duck.action.Animal;
import org.example.pattern.duck.action.impl.AbstractAnimal;

/**
 * @author: zyh
 * @date: 2022/9/30
 */
public class DryDuck extends AbstractAnimal implements Duck, Animal {

    private Action action;

    public DryDuck(Action action) {
        this.action = action;
    }

    @Override
    public void color() {
        System.out.println("黄色");
    }

    public void swim(){
        action.swim();
    }

    @Override
    public void play(){
        System.out.println("旱鸭子行走速度80");
    }


}

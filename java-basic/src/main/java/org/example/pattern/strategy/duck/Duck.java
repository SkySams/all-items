package org.example.pattern.strategy.duck;

import org.example.pattern.strategy.behavior.FlyBehavior;
import org.example.pattern.strategy.behavior.QuackBehavior;

public abstract class Duck {

    QuackBehavior quackBehavior;

    FlyBehavior flyBehavior;

    public Duck() {
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public abstract void display();


    // 委托给行为类
    public void performQuack(){
        quackBehavior.quack();
    }

    public void performFly(){
        flyBehavior.fly();
    }

    public void swim (){
        System.out.println("All ducks float , event decoys");
    }
}

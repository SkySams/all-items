package org.example.pattern.strategy.behavior;

/**
 * 不会飞的鸭子动作实现类
 */
public class FlyNoWay implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("I con't fly");
    }

}

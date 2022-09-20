package org.example.pattern.strategy;

/**
 * @author: zyh
 * @date: 2022/9/20
 */
public class ConcreteStrategyA implements Strategy{
    @Override
    public void strategyMethod() {
        System.out.println("ConcreteStrategyA -> strategyMethod");
    }
}

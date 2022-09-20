package org.example.pattern.strategy;

/**
 * @author: zyh
 * @date: 2022/9/20
 */
public class ConcreteStrategyB implements Strategy{
    @Override
    public void strategyMethod() {
        System.out.println("ConcreteStrategyB -> strategyMethod");
    }
}

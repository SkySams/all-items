package org.example.pattern.strategy;

import lombok.Data;

/**
 * @author: zyh
 * @date: 2022/9/20
 */
@Data
public class Context {

    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void strategyMethod(){
        strategy.strategyMethod();
    }

}

package org.example.pattern.strategy.one;

import lombok.Data;

/**
 *
 * @author: zyh
 * @date: 2022/9/20
 */
@Data
public class Environment {

    private Calculate calculate;

    public Environment(Calculate calculate) {
        this.calculate = calculate;
    }

    public void print(int numx,int numy){
        System.out.println(calculate.calc(numx,numy));
    }
}

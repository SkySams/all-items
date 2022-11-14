package org.example.pattern.factory.pizza;

/**
 * @author: zyh
 * @date: 2022/11/14
 */
public class GreekPizza extends Pizza{
    @Override
    void bake() {
        System.out.println("GreekPizza bake");
    }

    @Override
    void box() {
        System.out.println("GreekPizza box");
    }

    @Override
    void cut() {
        System.out.println("GreekPizza cut");
    }

    @Override
    void prepare() {
        System.out.println("GreekPizza prepare");
    }
}

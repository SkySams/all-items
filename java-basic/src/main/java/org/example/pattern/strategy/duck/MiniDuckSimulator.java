package org.example.pattern.strategy.duck;

public class MiniDuckSimulator {

    /**
     * 设计原则： 多用组合，少用继承
     * 准对接口开发、而不是实现编程
     * 封装变化跟不变化
     * @param args
     */
    public static void main(String[] args) {
        Duck mallardDuck = new MallardDuck();
        mallardDuck.performFly();
        mallardDuck.performQuack();
    }

}

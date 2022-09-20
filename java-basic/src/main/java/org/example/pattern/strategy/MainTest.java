package org.example.pattern.strategy;

/**
 * @author: zyh
 * @date: 2022/9/20
 */
public class MainTest {

    /**
        策略模式： 定义一系列算法，并将算法封装起来
          优点：算法自由切换，避免使用多重条件判断，扩展性好
          缺点：策略类增多，所有的策略类会向外暴露
     */
    public static void main(String[] args) {
        Context context = new Context(new ConcreteStrategyA());
        context.strategyMethod();

        context.setStrategy(new ConcreteStrategyB());
        context.strategyMethod();
    }

}

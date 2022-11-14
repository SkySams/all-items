package org.example.pattern.factory;

import org.example.pattern.factory.pizza.LDCheesePizza;
import org.example.pattern.factory.pizza.LDPepperPizza;
import org.example.pattern.factory.pizza.Pizza;

/**
 * @author: zyh
 * @date: 2022/11/14
 */
public class LDFactory implements OrderPizza{
    @Override
    public Pizza createPizza(String type) {
        Pizza pizza = null;
        if ("cheese".equals(type)) {
            pizza = new LDCheesePizza();
        } else if ("pepper".equals(type)) {
            pizza = new LDPepperPizza();
        }
        return pizza;
    }
}

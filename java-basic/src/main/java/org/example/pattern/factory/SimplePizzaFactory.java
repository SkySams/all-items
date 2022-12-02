package org.example.pattern.factory;

import org.example.pattern.factory.pizza.CheesePizza;
import org.example.pattern.factory.pizza.GreekPizza;
import org.example.pattern.factory.pizza.PepperPizza;
import org.example.pattern.factory.pizza.Pizza;

/**
 * @author: zyh
 * @date: 2022/11/14
 */
public class SimplePizzaFactory implements OrderPizza{

    @Override
    public Pizza createPizza(String type){
        Pizza pizza = null;
        if (type.equals("cheese")) {
            pizza = new CheesePizza();
        } else if (type.equals("greek")) {
            pizza = new GreekPizza();
        } else if (type.equals("pepper")) {
            pizza = new PepperPizza();
        }
        return pizza;
    }

}

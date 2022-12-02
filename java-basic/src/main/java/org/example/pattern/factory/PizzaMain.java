package org.example.pattern.factory;

import org.example.pattern.factory.pizza.Pizza;
import org.junit.Test;

/**
 * @author: zyh
 * @date: 2022/11/14
 */
public class PizzaMain {

    @Test
    public void simple(){
        SimplePizzaFactory factory = new SimplePizzaFactory();
        Pizza pizza = factory.createPizza("greek");
        pizza.settlement();
    }

}

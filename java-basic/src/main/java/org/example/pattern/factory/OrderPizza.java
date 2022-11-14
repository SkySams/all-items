package org.example.pattern.factory;

import org.example.pattern.factory.pizza.Pizza;

/**
 * @author: zyh
 * @date: 2022/11/14
 */
public interface OrderPizza {

    Pizza createPizza(String type);

}

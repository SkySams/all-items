package org.example.pattern.duck.action.impl;

import cn.hutool.core.lang.Console;
import org.example.pattern.duck.action.Action;

/**
 * @author: zyh
 * @date: 2022/9/30
 */
public class DryDuckAction implements Action {

    @Override
    public void swim() {
        Console.log("是旱鸭子不会游泳");
    }
}

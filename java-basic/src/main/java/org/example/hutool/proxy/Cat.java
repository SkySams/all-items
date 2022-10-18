package org.example.hutool.proxy;

import cn.hutool.core.lang.Console;

import java.io.Serializable;

/**
 * @author: zyh
 * @date: 2022/9/29
 */
public class Cat implements Animal, Serializable {
    @Override
    public void eat() {
//        System.out.println("猫爱吃鱼......");
        Console.log("猫吃鱼");
    }
}

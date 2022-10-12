package org.example.hutool.proxy;

import cn.hutool.core.lang.Console;

/**
 * @author: zyh
 * @date: 2022/9/29
 */
public class Cat implements Animal{
    @Override
    public void eat() {
//        System.out.println("猫爱吃鱼......");
        Console.log("猫吃鱼");
    }
}

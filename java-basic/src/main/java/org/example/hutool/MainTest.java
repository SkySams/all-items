package org.example.hutool;

import cn.hutool.aop.ProxyUtil;
import cn.hutool.aop.aspects.TimeIntervalAspect;
import org.example.hutool.proxy.Animal;
import org.example.hutool.proxy.Cat;
import org.example.hutool.proxy.Dog;

/**
 * @author: zyh
 * @date: 2022/9/29
 */
public class MainTest {

    public static void main(String[] args) {
        Animal cat = ProxyUtil.proxy(new Cat(), TimeIntervalAspect.class);
        cat.eat();

        Dog dog = ProxyUtil.proxy(new Dog(),TimeIntervalAspect.class);
        dog.eat();
    }
}

package org.example.design.adaptation.classs;

import org.example.design.adaptation.Target;

/**
 * @author: zyh
 * @date: 2022/6/6
 */
public class ConcreteTarget implements Target {
    /**
     * 类的适配器模式、对象的适配器我模式、接口适配器模式
     */
    @Override
    public void request() {
        System.out.println("普通类 具有 普通功能...");
    }
}

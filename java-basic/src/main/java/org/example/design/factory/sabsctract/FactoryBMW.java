package org.example.design.factory.sabsctract;

import org.example.design.factory.single.BMW;

/**
 * @author: zyh
 * @date: 2022/4/13
 *

  抽象工厂 AbstractFactory： 工厂方法模式的核心，是具体工厂角色必须实现的接口或者必须继承的父类，在 Java 中它由抽象类或者接口来实现。

 */
public interface FactoryBMW {

    BMW createBMW();
}

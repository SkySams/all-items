package org.example.design.factory.single;

import org.example.design.factory.enums.BMWType;

/**
 * @author: zyh
 * @date: 2022/4/13
 */
public class SingleFactory {

    /**
     * 简单的工厂模式
     * 客户跟宝马降低了耦合性

     优点：
     1、提供工厂类创建对象，实现了创建对象跟职责分离
     2、客户不需要知道工程是怎样生产宝马的
     3、在不改变代码情况下更换和新增新的具体产品， 在一定成都上提供了系统的灵活性

     缺点：
     1、缺点在于不符合“开闭原则”， 每次更改都需要改工厂类
     2、在产品类型较多时，有可能造成工厂逻辑过于复杂，不利于系统的扩展维护，并且工厂类集中了所有产品创建逻辑，一旦不能正常工作，整个系统都要受到影响


     * @param args
     */
    public static void main(String[] args) {
       BMW320 bmw320 = (BMW320) FactoryBMW.createBMW(BMWType.BMW_320);
       BMW520 bmw520 = (BMW520) FactoryBMW.createBMW(BMWType.BMW_520);
    }

}





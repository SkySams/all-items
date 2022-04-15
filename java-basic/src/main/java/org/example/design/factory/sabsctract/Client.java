package org.example.design.factory.sabsctract;

import org.example.design.factory.single.BMW;

/**
 * @author: zyh
 * @date: 2022/4/15
 */
public class Client {


    /**

     抽象工厂 AbstractFactory： 比如大型工厂
     具体工厂 Factory：生产流水线
     抽象产品 AbstractProduct：有多条流水线
     具体产品 Product：流水线上的产品

     */
    public static void main(String[] args) {
        FactoryBMW320 factoryBMW320 = new FactoryBMW320();
        BMW bmw = factoryBMW320.createBMW();

        FactoryBMW520 factoryBMW520 = new FactoryBMW520();
        BMW bmw520 = factoryBMW520.createBMW();
    }

}

package org.example.design.factory.abstracts;

/**
 * @author: zyh
 * @date: 2022/4/15
 */
public class InnocateClient {

    /**

     1、工厂方法模式与抽象工厂模式的区别在于：
     （1）工厂方法只有一个抽象产品类和一个抽象工厂类，
     但可以派生出多个具体产品类和具体工厂类，每个具体工厂类只能创建一个具体产品类的实例。

     （2）抽象工厂模式拥有多个抽象产品类（产品族）和一个抽象工厂类，
      每个抽象产品类可以派生出多个具体产品类；抽象工厂类也可以派生出多个具体工厂类，
      同时每个具体工厂类可以创建多个具体产品类的实例

     */
    public static void main(String[] args) {
        InnovateFactoryBMW523 innovateFactoryBMW523 = new InnovateFactoryBMW523();
        innovateFactoryBMW523.createAircondition();
        innovateFactoryBMW523.createEngine();
    }




}

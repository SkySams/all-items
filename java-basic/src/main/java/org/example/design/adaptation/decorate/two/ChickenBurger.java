package org.example.design.adaptation.decorate.two;

/**
 * @author: zyh
 * @date: 2022/6/8
 */
public class ChickenBurger extends Humburger {

    public ChickenBurger() {
        this.name = "鸡腿堡";
    }

    @Override
    public double getPrice() {
        return 10;
    }
}

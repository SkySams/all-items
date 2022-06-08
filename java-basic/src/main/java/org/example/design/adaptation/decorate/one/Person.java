package org.example.design.adaptation.decorate.one;

/**
 * @author: zyh
 * @date: 2022/6/8
 */
public class Person implements Human{
    /**
     *
     */
    @Override
    public void wearClothes() {
        System.out.println("穿什么衣服呢");
    }
}

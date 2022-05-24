package org.example.design.proxy.flower;

/**
 * 玫瑰花
 * @author: zyh
 * @date: 2022/5/24
 */
public class RoseFlower implements Flower {
    @Override
    public void floral() {
        System.out.println("购买了甜甜的玫瑰花");
    }
}

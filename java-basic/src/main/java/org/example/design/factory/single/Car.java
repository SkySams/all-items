package org.example.design.factory.single;

/**
 * @author: zyh
 * @date: 2022/4/13
 */
public class Car {

    /**
     * 在没有工厂的时代，如果客户需要一款宝马车，
     * 那么就需要客户去创建一款宝马车，然后拿来用。
     *
     * 缺点： 客户跟宝马车是紧密耦合的关系
     * @param args
     */
    public static void main(String[] args) {
        new BMW320();
        new BMW520();
    }

}

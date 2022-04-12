package org.example.design.factory.fruit;

/**
 * @author: zyh
 * @date: 2022/4/12
 */
public abstract class AbstactFruit implements Fruit {

    int anInt;

    @Override
    public int size(){
        return anInt;
    }
}

package org.example.design.adaptation.decorate.one;

/**
 * 定义装饰者
 * @author: zyh
 * @date: 2022/6/8
 */
public abstract class Decorator implements Human{

    private Human human;

    public Decorator(Human human) {
        this.human = human;
    }

    /**
     *
     */
    @Override
    public void wearClothes() {
        human.wearClothes();
    }
}

package org.example.design.adaptation.decorate.one;

/**
 * @author: zyh
 * @date: 2022/6/8
 */
public class Decorator_one extends Decorator {

    public Decorator_one(Human human) {
        super(human);
    }
    public void goClothespress() {
        System.out.println("去衣柜找找看。。");
    }

        /**
         *
         */
    @Override
    public void wearClothes() {
        super.wearClothes();
        this.goClothespress();
    }
}

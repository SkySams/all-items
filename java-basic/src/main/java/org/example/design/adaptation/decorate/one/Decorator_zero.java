package org.example.design.adaptation.decorate.one;

/**
 * @author: zyh
 * @date: 2022/6/8
 */
public class Decorator_zero extends Decorator{

    public Decorator_zero(Human human) {
        super(human);
    }

    public void goHome(){
        System.out.println("进房子。。");
    }

    /**
     *
     */
    @Override
    public void wearClothes() {
        super.wearClothes();
        this.goHome();
    }
}

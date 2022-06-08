package org.example.design.adaptation.decorate.two;

/**
 * @author: zyh
 * @date: 2022/6/8
 */
public class Lettuce extends Condiment{

    Humburger humburger;

    public Lettuce(Humburger humburger){
        this.humburger = humburger;
    }


    @Override
    public String getName() {
        return humburger.getName()+" 加生菜";
    }

    @Override
    public double getPrice() {
        return humburger.getPrice()+1.5;
    }
}

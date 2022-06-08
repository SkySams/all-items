package org.example.design.adaptation.decorate.two;

/**
 * @author: zyh
 * @date: 2022/6/8
 */
public class Chilli extends Condiment{

    Humburger humburger;

    public Chilli(Humburger humburger){
        this.humburger = humburger;

    }


    @Override
    public String getName() {
        return humburger.getName()+" 加辣椒";
    }

    @Override
    public double getPrice() {
        return humburger.getPrice();
    }
}

package org.example.pattern.decorator;


/**
 * 摩卡咖啡
 */
public class Mocha extends CondimentDecorator{

    double price = 200;

    Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        return price+beverage.cost();
    }

    @Override
    public String getDescription() {
        System.out.println("Mocha price："+ price);
        return beverage.getDescription()+", Mocha";
    }
}

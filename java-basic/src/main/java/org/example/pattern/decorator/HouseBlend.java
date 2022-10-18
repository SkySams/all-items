package org.example.pattern.decorator;

public class HouseBlend extends Beverage {

    Beverage beverage;

    public HouseBlend(Beverage beverage) {
        this.beverage = beverage;
    }

    public HouseBlend() {
        description = "HouseBlend";
    }

    @Override
    public double cost() {
        return 9;
    }
}

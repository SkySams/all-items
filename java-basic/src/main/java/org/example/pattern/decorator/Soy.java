package org.example.pattern.decorator;

public class Soy extends CondimentDecorator{

    Beverage beverage;

    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        return 95;
    }

    @Override
    public String getDescription() {
        return "Soy";
    }
}

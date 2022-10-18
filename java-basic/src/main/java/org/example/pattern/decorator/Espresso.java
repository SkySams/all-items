package org.example.pattern.decorator;

/**
 * 意大利咖啡
 */
public class Espresso extends Beverage {

    public Espresso() {
        description = "Espresso";
    }

    @Override
    public double cost() {
        return 992;
    }
}

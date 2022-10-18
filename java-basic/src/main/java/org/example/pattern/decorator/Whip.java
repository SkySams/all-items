package org.example.pattern.decorator;

public class Whip extends CondimentDecorator{

    @Override
    public double cost() {
        return 0;
    }

    @Override
    public String getDescription() {
        return null;
    }

}

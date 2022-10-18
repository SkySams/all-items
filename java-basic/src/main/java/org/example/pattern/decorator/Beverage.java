package org.example.pattern.decorator;

/**
 * 饮料 基类
 */
public abstract class Beverage {

    static String description  = "Unkonwn Beverage";


    public String getDescription(){
        return description;
    }

    public abstract double cost();

}

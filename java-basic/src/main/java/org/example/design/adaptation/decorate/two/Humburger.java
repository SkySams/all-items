package org.example.design.adaptation.decorate.two;

/**
 * 定义被装饰者
 * @author: zyh
 * @date: 2022/6/8
 */
public abstract class Humburger {

    protected  String name ;

    public String getName(){
        return name;
    }
    public abstract double getPrice();

}

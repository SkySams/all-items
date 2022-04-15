package org.example.design.factory.adaptation.classadaptation;

/**
 * @author: zyh
 * @date: 2022/4/15
 */
public class ConcreteTarget implements Target{
    @Override
    public void request() {
        System.out.println("普通类 具有 普通功能...");
    }
}

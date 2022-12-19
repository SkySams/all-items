package org.example.pattern.adapter;

/**
 * @author: zyh
 * @date: 2022/12/19
 */
public class ConcreteTarget implements Target{

    @Override
    public void request() {
        System.out.println("ConcreteTarget ");
    }
}

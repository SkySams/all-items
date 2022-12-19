package org.example.pattern.adapter;

/**
 * 适配器
 * @author: zyh
 * @date: 2022/12/19
 */
public class AdapterObject implements Target {

    /**
     * 对象适配器
     */
    private Adaptee adaptee = new Adaptee();

    @Override
    public void request(){
        adaptee.adapteeRequest();
    }

}

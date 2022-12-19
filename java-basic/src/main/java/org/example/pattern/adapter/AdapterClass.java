package org.example.pattern.adapter;

/**
 * @author: zyh
 * @date: 2022/12/19
 */
public class AdapterClass extends Adaptee implements Target{

    /**
     * 类适配器
     */

    @Override
    public void request() {
        super.adapteeRequest();
    }
}

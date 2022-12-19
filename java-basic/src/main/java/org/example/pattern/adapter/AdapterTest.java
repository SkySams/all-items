package org.example.pattern.adapter;

import org.junit.Test;

/**
 * @author: zyh
 * @date: 2022/12/19
 */
public class AdapterTest {

    @Test
    public void one(){
        ConcreteTarget target = new ConcreteTarget();
        target.request();

        AdapterObject adapter = new AdapterObject();
        adapter.request();
    }

}

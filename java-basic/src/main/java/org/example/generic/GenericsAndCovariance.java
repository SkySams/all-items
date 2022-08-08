package org.example.generic;

import org.example.generic.test.Apple;
import org.example.generic.test.Fruit;
import org.example.generic.test.Orange;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zyh
 * @date: 2022/8/6
 */
public class GenericsAndCovariance {

    @Test
    public void ganerics(){

        List<? extends Fruit> flist = new ArrayList<>();
        // Error: can't add any type of object
//        flist.add(new Apple());
//        flist.add(new Orange());
//        flist.add(new Fruit());
//        flist.add(new Object());
        flist.add(null);
        flist.get(0);
    }

}

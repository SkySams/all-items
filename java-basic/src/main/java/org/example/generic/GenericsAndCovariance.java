package org.example.generic;

import org.example.generic.test.Apple;
import org.example.generic.test.Fruit;
import org.example.generic.test.Orange;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: zyh
 * @date: 2022/8/6
 */
public class GenericsAndCovariance {

    @Test
    public void ganerics(){

        List<? extends Fruit> flist = new ArrayList<Apple>();
        // Error: can't add any type of object
//        flist.add(new Apple());
//        flist.add(new Orange());
//        flist.add(new Fruit());
//        flist.add(new Object());
        flist.add(null);
        flist.get(0);

        Object[] strings = new String[2];
        strings[0] = "hi";
        strings[1] = 100;
        System.out.println(strings);
    }

    @Test
    public void one (){
        List<String>  list = new ArrayList<>();
        list.add("2");
        list.add("1");
//        Collections.fill(list,"Hello");
        Collections.sort(list, String::compareTo);
        for (String item : list){
            System.out.println(item);
        }

    }

}

package org.example.generic;

import org.junit.Test;

/**
 * @author: zyh
 * @date: 2022/8/6
 */
public class GenericParamTest {





    @Test
    public void two(){
        Pair<Integer,String> pair1 = new Pair<>(1,"jack");
        Pair<Integer,String> pair2 = new Pair<>(2,"lixi");
        boolean result = Util.compara(pair1,pair2);
        System.out.println(result);
    }


    @Test
    public void one(){
        Box<Integer> one = new Box<>();
        one.set(23);
        System.out.println(one.get());
        Box<Double> two = new Box<>();
        Box<Float> three = new Box<>();
    }

}

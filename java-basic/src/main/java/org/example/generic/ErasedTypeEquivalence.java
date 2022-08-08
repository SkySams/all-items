package org.example.generic;

import java.util.ArrayList;

/**
 * @author: zyh
 * @date: 2022/8/8
 */
public class ErasedTypeEquivalence {

    public static void main(String[] args) {
        Class c1 = new ArrayList<String>().getClass();
        Class c2 = new ArrayList<Integer>().getClass();
        System.out.println(c2 == c1);
    }

}

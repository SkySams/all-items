package org.example.generic.program;

import java.io.Serializable;

public class ArrayAlg {

    public static <T> T getMiddle(T... a){
        return a[a.length/2];
    }

    public static <T extends Comparable<T> & Serializable > T min(T[] a){
        if (a == null || a.length == 0){
            return null;
        }
        T smallest = a[0];
        for(int i = 1; i < a.length; i++){
            if (smallest.compareTo(a[i]) > 0){
                smallest = a[i];
            }
        }
        return smallest;
    }

    public static void main(String[] args) {
        String result = ArrayAlg.getMiddle("Jack","luxi","hanshu");
        System.out.println(result);
        // Eoort: Incompatible types: Number & Comparable<? extends Number & Comparable<?>> is not convertible to double
//        double middle = ArrayAlg.getMiddle(3.14,1719,0);
//        System.out.println(middle);
    }

}

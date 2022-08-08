package org.example.generic;

import org.example.generic.test.Apple;
import org.example.generic.test.Fruit;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zyh
 * @date: 2022/8/6
 */
public class GenericsWriting {

    static List<Apple> apples = new ArrayList<>();
    static List<Fruit> fruits = new ArrayList<>();

    static <T> void writeExact(List<T> list, T elem){
        list.add(elem);
    }

    static void f1(){
        writeExact(apples,new Apple());
        writeExact(fruits,new Apple());
    }

    static <T> void writeWildcard(List<? super T> list, T item){
        list.add(item);
    }

    static void f2(){
        writeWildcard(apples,new Apple());
        writeWildcard(fruits, new Apple());
    }

    public static void main(String[] args) {
        f1(); f2();
    }

}

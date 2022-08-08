package org.example.generic;

import org.example.generic.test.Apple;
import org.example.generic.test.Fruit;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author: zyh
 * @date: 2022/8/6
 */
public class GenericReading {
    static List<Apple> apples = Arrays.asList(new Apple());
    static List<Fruit> fruits = Arrays.asList(new Fruit());

    static class Reader<T> {
        T readExact(List<T> list){
            return list.get(0);
        }
    }

    static class CovariantReader<T>{
        T readCovariant(List<? extends T> list){
            return list.get(0);
        }
    }

    static class CovariantReaderSuper<T>{
        T readCovariantSuper(List<? super T> list){
            return (T) list.get(0);
        }
    }

    static void f1(){
        Reader<Fruit> fruitReader = new Reader<>();
//        Fruit fruit = fruitReader.readExact(apples);
    }

    static void f2(){
        CovariantReader<Fruit> fruitReader = new CovariantReader<Fruit>();
        Fruit f = fruitReader.readCovariant(fruits);
        Fruit a = fruitReader.readCovariant(apples);
    }

    static void f3(){
        CovariantReaderSuper<Fruit> readerSuper = new CovariantReaderSuper();
        readerSuper.readCovariantSuper(fruits);
//        readerSuper.readCovariantSuper(apples);
    }

    @Test
    public void test_one(){
        f1();
        f2();
        f3();
    }
}

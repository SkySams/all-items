package org.example.generic;

import java.util.List;

/**
 * @author: zyh
 * @date: 2022/8/6
 */
public class Util {

    /**
     * 泛型方法
     * @param pair1
     * @param pair2
     * @return
     */
    public static boolean compara(Pair pair1,Pair pair2){
        return pair1.getKey().equals(pair2.getKey()) && pair1.getValue().equals(pair2.getValue());
    }

    /**
     * 边界符
     * 查找一个泛型数组大于某个特定元素的个数
     */
    public static <T> int countGreaterThat(T[] array, T elem){
        int count = 0;
        for (T e : array){
            /**
              原因：因为除了short, int, double, long, float, byte, char等原始类型，其他的类并不一定能使用操作符>，所以编译器报错。
              怎么解决？
              使用边界符
             */
//            if (e > elem){ // compiler error
//                ++count;
//            }
        }
        return count;
    }

    /**
     * 边界符
     * @param anArray
     * @param elem
     * @param <T>
     * @return
     */
    public static <T extends Comparable<T> > int countGreaterThatNew(T[] anArray,T elem){
        int count = 0;
        for (T e : anArray){
            if (e.compareTo(elem) > 0){
                ++count;
            }
        }
        return count;
    }

    public static <E> void append(List<E> list){
//        E elem = new E(); // type parameter 'E' cannot be instantiated directly
//        list.add(elem);
    }

    public static <E> void append(List<E> list,Class<E> cla) throws InstantiationException, IllegalAccessException {
        E e = cla.newInstance();
        list.add(e);
    }



}

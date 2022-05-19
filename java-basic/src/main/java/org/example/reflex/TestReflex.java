package org.example.reflex;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.annotation.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * https://blog.csdn.net/qq_44715943/article/details/120587716
 *
 * @author: zyh
 * @date: 2022/5/17
 */
public class TestReflex {

    public TestReflex(){}


//        AtomicReference<BigDecimal> storeMoney = new AtomicReference<>(BigDecimal.ZERO);
//        storeMoney.set(BigDecimal.valueOf(12));
//        storeMoney.set(BigDecimal.valueOf(10).add(storeMoney.get()));
//        System.out.println(storeMoney.get());

//        List<User> list = new ArrayList<>();
//        list.add(new User(23));
//        list.add(new User(23));
//        Long count = list.stream().collect(Collectors.summingLong(User::getNum));
//        System.out.println(count);


    /**
     * test
     *
     * @param args
     */
    public static void main(String[] args) {

//        List<User> list = new ArrayList<>();
//        list.add(new User(23));
//        list.add(new User(23));
//         Iterator<User> iter = list.iterator();
//         while (iter.hasNext()){
//            User sd = iter.next();
//            sd.setNum(12);
//         }
//        System.out.println(list.size());
//        Long count = list.stream().collect(Collectors.summingLong(User::getNum));
//        System.out.println(count);


        Set<String> set1 = new HashSet<>();
        set1.add("1");

        Set<String> set2 = new HashSet<>();
        set2.add("1");
        set2.add("2");
        set2.add("3");


        System.out.println(set2.contains("1"));
        long count =set2.stream().filter(in -> !set1.contains(in)).count();
        System.out.println(count);
        TestReflex  estReflex = new TestReflex();
        estReflex.test_two();

    }

    /**
     * 获取到Class，能干什么？
     * 通过Class的newInstance()方法来实例化对象。
     * 注意：newInstance()方法内部实际上调用了无参数构造方法，必须保证无参构造存在才可以。
     */
    public void test_two() {
        try {
//            Class cla = Class.forName("org.example.reflex.MyReflex");
            Class cla = Class.forName("org.example.reflex.TestReflex", true, this.getClass().getClassLoader());
            System.out.println(cla);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 简答测试
     */
    public static void test_one() {
        try {


            Class c1 = Class.forName("java.lang.String");
            Class c2 = Class.forName("java.lang.Integer");
            Class c3 = Class.forName("java.util.Date");


            String a = "abc";
            Class c4 = a.getClass();
            System.out.println(c4);
            System.out.println(c4 == c1);


            Date time = new Date();
            Class c5 = time.getClass();
            System.out.println(c5 == c3);

            Class i = Integer.class;
            Class d = Double.class;
            Class f = Float.class;
            System.out.println(i == c2);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}


class User {
    public User() {
    }

    public User(Integer num) {
        this.num = num;
    }

    private Integer num;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}

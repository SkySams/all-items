package org.example.reflex;


/*
 * 通过Class对象可以获取某个类中的：构造方法、成员变量、成员方法；并访问成员；
 *
 * 1.获取构造方法：
 * 		1).批量的方法：
 * 			public Constructor[] getConstructors()：所有"公有的"构造方法
            public Constructor[] getDeclaredConstructors()：获取所有的构造方法(包括私有、受保护、默认、公有)
 * 		2).获取单个的方法，并调用：
 * 			public Constructor getConstructor(Class... parameterTypes):获取单个的"公有的"构造方法：
 * 			public Constructor getDeclaredConstructor(Class... parameterTypes):获取"某个构造方法"可以是私有的，或受保护、默认、公有；
 * 		3).调用构造方法：
 * 			Constructor-->newInstance(Object... initargs)
 */

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Constructors {

    public static void main(String[] args) {
        try {
            // 加载Clazz 对象
            Class clazz = Class.forName("org.example.reflex.Student");
            Constructor[] constructors = clazz.getConstructors();
            System.out.println("**********************所有公有构造方法*********************************");
            for (Constructor con : constructors) {
                System.out.println(con);
            }

            System.out.println("************所有的构造方法(包括：私有、受保护、默认、公有)***************");
            Constructor[] constru = clazz.getDeclaredConstructors();
            for (Constructor con : constru) {
                System.out.println(con);
            }
            System.out.println("*****************获取公有、无参的构造方法*******************************");
            Constructor zero = clazz.getConstructor(null);
            System.out.println(zero);

            System.out.println("*****************获取公有、无参的构造方法*******************************");
            //调用构造方法
            Student obj = (Student) zero.newInstance();

            // 不是公共构造函数不可被创建
            System.out.println("******************获取公共构造方法，并调用*******************************");
            Constructor con= clazz.getConstructor(char.class);
            System.out.println(con);

            con.setAccessible(false);
            con.newInstance('男');

            Constructor constructor = clazz.getDeclaredConstructor(int.class);
            constructor.setAccessible(true);
            constructor.newInstance(5000);



        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}

package org.example.reflex;

import java.io.Serializable;

/**
 * @author: zyh
 * @date: 2022/6/9
 */
public class Student implements Serializable {
    //---------------构造方法-------------------
    Student(String str) {
        System.out.println("(默认)的构造方法 s = " + str);
    }

    public Student() {
        System.out.println("调用了公有、无参构造方法执行了。。。");
    }

    public Student(char name) {
        System.out.println("姓名：" + name);
    }

    public Student(String name, int age) {
        //这的执行效率有问题，以后解决。
        System.out.println("姓名：" + name + "年龄：" + age);
    }

    protected Student(boolean n) {
        System.out.println("受保护的构造方法 n = " + n);
    }

    private Student(int age) {
        System.out.println("私有的构造方法   年龄：" + age);
    }
}

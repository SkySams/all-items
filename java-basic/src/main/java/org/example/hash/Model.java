package org.example.hash;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author: zyh
 * @date: 2022/6/10
 */
public class Model {

    private String name;
    private double salary;
    private int sex;


    /**
     java 7 中对hashCode()方法做了两个改进。
     Objects.hashCode, 如果参数为null 返回为0
     Objects.hash()

     equals 与 hashComde 关系
     1、如果两个对象的equals 相等，hashCode 一定相等
     2、如果两个对象的hashCode 相等，equals 不一定相等，只能说明两个对象在一个散列存储结构中
     3、如果重写equals方法，那么对象hashCode 也要需要重写

     */
    @Override
    public int hashCode() {
//        return name.hashCode()+new Double(salary).hashCode()+new Integer(sex).hashCode();
//        return Objects.hashCode(name) + new Double(salary).hashCode() + new Integer(sex).hashCode();
        return Objects.hash(name,salary,sex);
    }
}

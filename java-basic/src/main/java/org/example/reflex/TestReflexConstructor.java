package org.example.reflex;

import java.lang.reflect.Constructor;
import java.util.Arrays;

/**
 * @author: zyh
 * @date: 2022/5/16
 */
public class TestReflexConstructor {

    /**
     * public String getName()	返回构造方法名
     * public int getModifiers()	获取构造方法的修饰符列表,返回的修饰符是一个数字，每个数字是修饰符的代号【一般配合Modifier类的toString(int x)方法使用】
     * public Class<?>[] getParameterTypes()	返回构造方法的修饰符列表（一个方法的参数可能会有多个。）【结果集一般配合Class类的getSimpleName()方法使用】
     * public T newInstance(Object … initargs)	创建对象【参数为创建对象的数据】
     */
    public static void main(String[] args) {
        try {
           Class cla = Class.forName("org.example.reflex.MyReflex");
           Constructor[] constructors = cla.getDeclaredConstructors();
            System.out.println(Arrays.asList(constructors));

            // 获取无参构造函数


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

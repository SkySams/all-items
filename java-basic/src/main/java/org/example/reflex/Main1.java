package org.example.reflex;

import java.lang.reflect.Array;
import java.lang.reflect.Method;

/**
    获取MainTest类的main方法、不要与当前的main方法搞混了
 */
public class Main1 {

    public static void main(String[] args) {
        try {
            //1、获取Student对象的字节码
            Class clazz = Class.forName("org.example.reflex.MainTest");

            //2、获取main方法
            //第一个参数：方法名称，第二个参数：方法形参的类型，
            Method methodMain = clazz.getMethod("main", String[].class);
            //3、调用main方法
            // methodMain.invoke(null, new String[]{"a","b","c"});
            //第一个参数，对象类型，因为方法是static静态的，所以为null可以，第二个参数是String数组，这里要注意在jdk1.4时是数组，jdk1.5之后是可变参数
            //这里拆的时候将  new String[]{"a","b","c"} 拆成3个对象。。。所以需要将它强转。
            methodMain.invoke(null, (Object)new String[]{"a","b","c"});//方式一
            // methodMain.invoke(null, new Object[]{new String[]{"a","b","c"}});//方式二
            testArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //利用反射创建数值
    public static void testArray() throws ClassNotFoundException {
        Class<?> cls = Class.forName("java.lang.String");
        Object array = Array.newInstance(cls,25);
        //往数组里添加内容
        Array.set(array,0,"golang");
        Array.set(array,1,"Java");
        Array.set(array,2,"pytho");
        Array.set(array,3,"Scala");
        Array.set(array,4,"Clojure");
        //获取某一项的内容
        System.out.println(Array.get(array,3));
    }




}

package org.example.reflex;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * @author: zyh
 * @date: 2022/5/16
 */
public class TestReflexMethod {

    /**
     * public String getName()	返回方法名
     * public int getModifiers()	获取方法的修饰符列表,返回的修饰符是一个数字，每个数字是修饰符的代号【一般配合Modifier类的toString(int x)方法使用】
     * public Class<?> getReturnType()	以Class类型，返回方法类型【一般配合Class类的getSimpleName()方法使用】
     * public Class<?>[] getParameterTypes()	返回方法的修饰符列表（一个方法的参数可能会有多个。）【结果集一般配合Class类的getSimpleName()方法使用】
     * public Object invoke(Object obj, Object… args)	调用方法
     */
    public static void main(String[] args) {
        StringBuilder s = new StringBuilder();
        try {

            Class cla = Class.forName("org.example.reflex.MyReflex");
            // 获取所有的Method（包括私有的！）
            Method[] methods = cla.getDeclaredMethods();
            System.out.println(Arrays.asList(methods));
            Object obg = cla.newInstance();

            Method loginMethod = cla.getDeclaredMethod("edit", String.class);
            System.out.println(loginMethod);
            Object resValues = loginMethod.invoke(obg,"hello world");
            if (resValues instanceof String){
                System.out.println(resValues);
            }



        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

}

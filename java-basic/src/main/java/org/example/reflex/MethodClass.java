package org.example.reflex;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author: zyh
 * @date: 2022/6/9
 */
public class MethodClass {


    /*
     * 获取成员方法并调用：
     *
     * 1.批量的：
     * 		public Method[] getMethods():获取所有"公有方法"；（包含了父类的方法也包含Object类）
     * 		public Method[] getDeclaredMethods():获取所有的成员方法，包括私有的(不包括继承的)
     * 2.获取单个的：
     * 		public Method getMethod(String name,Class<?>... parameterTypes):
     * 					参数：
     * 						name : 方法名；
     * 						Class ... : 形参的Class类型对象
     * 		public Method getDeclaredMethod(String name,Class<?>... parameterTypes)
     *
     * 	 调用方法：
     * 		Method --> public Object invoke(Object obj,Object... args):
     * 					参数说明：
     * 					obj : 要调用方法的对象；
     * 					args:调用方式时所传递的实参；
    :
     */

    public static void main(String[] args) {
        try {
            Class clazz = Class.forName("org.example.reflex.Human");
            System.out.println("***************获取所有的”公有“方法*******************");
            Method[] methods = clazz.getMethods();
            for (Method md : methods) {
                System.out.println(md);
            }
            System.out.println("***************获取所有的方法，包括私有的*******************");
            Method[] metOne = clazz.getDeclaredMethods();
            for (Method md : metOne) {
                System.out.println(md);
            }

           Object obj = clazz.getConstructor().newInstance();

            System.out.println("***************获取私有的show4()方法******************");
            Method me = clazz.getDeclaredMethod("show4",int.class);
            System.out.println(me);
            // 解除限定
            me.setAccessible(true);
            Object result = me.invoke(obj,20);
            System.out.println(result);

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

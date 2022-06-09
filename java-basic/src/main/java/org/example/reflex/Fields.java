package org.example.reflex;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @author: zyh
 * @date: 2022/6/9
 */
public class Fields {


    /*
     * 获取成员变量并调用：
     *
     * 1.批量的
     * 		1).Field[] getFields():获取所有的"公有字段"
     * 		2).Field[] getDeclaredFields():获取所有字段，包括：私有、受保护、默认、公有；
     * 2.获取单个的：
     * 		1).public Field getField(String fieldName):获取某个"公有的"字段；
     * 		2).public Field getDeclaredField(String fieldName):获取某个字段(可以是私有的)
     *
     * 	 设置字段的值：
     * 		Field --> public void set(Object obj,Object value):
     * 					参数说明：
     * 					1.obj:要设置的字段所在的对象；
     * 					2.value:要为字段设置的值；
     */

    public static void main(String[] args) {
        try {
            Class clazz = Class.forName("org.example.reflex.Human");

            System.out.println("************获取所有公有的字段********************");
            Field[] fields = clazz.getFields();
            for (Field fiel : fields){
                System.out.println(fiel);
            }
            System.out.println("************获取所有的字段(包括私有、受保护、默认的)********************");
            Field[] one = clazz.getDeclaredFields();
            for (Field fiel : one){
                System.out.println(fiel);
            }
            System.out.println("*************获取公有字段**并调用***********************************");
            Field field = clazz.getField("name");
            System.out.println(field);

            Object obj = clazz.getConstructor().newInstance();
            field.set(obj,"sky");

            // 验证
            Human stu = (Human)obj;
            System.out.println("验证姓名：" + stu.name);


            System.out.println("**************获取私有字段****并调用********************************");
            field = clazz.getDeclaredField("phoneNum");
            System.out.println(field);
            //暴力反射，解除私有限定
            field.setAccessible(true);
            field.set(obj,"1598654");
            System.out.println("验证电话：" + stu);


        } catch (ClassNotFoundException | NoSuchFieldException | NoSuchMethodException e) {
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

package org.example.reflex;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author: zyh
 * @date: 2022/5/16
 */
public class TestReflexField {

    /**
     * public String getName()	返回属性名
     * public int getModifiers()	获取属性的修饰符列表,返回的修饰符是一个数字，每个数字是修饰符的代号【一般配合Modifier类的toString(int x)方法使用】
     * public Class<?> getType()	以Class类型，返回属性类型【一般配合Class类的getSimpleName()方法使用】
     * public void set(Object obj, Object value)	设置属性值
     * public Object get(Object obj)	读取属性值
     */

    public static void main(String[] args) {
        test_relfex_two();
    }

    public static void test_relfex_two(){
        MyReflex myReflex = new MyReflex();
        myReflex.setNo(1023);
        System.out.println(myReflex.getNo());

        //使用反射机制给属性赋值
        try {
            Class cla = Class.forName("org.example.reflex.MyReflex");
            try {
                Object obj = cla.newInstance();
                Field noField = cla.getDeclaredField("no");
                // 如果修饰private, 需要更改成 Accessible
                noField.setAccessible(true);
                noField.set(obj,6666);
                System.out.println(noField.get(obj));
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void test_relfex_one(){
        StringBuilder s = new StringBuilder();
        try {
            Class myrelfex = Class.forName("org.example.reflex.MyReflex");
            s.append(Modifier.toString(myrelfex.getModifiers()) + " class " + myrelfex.getSimpleName() + " {\n");
            //获取所有的属性
            Field[] fields = myrelfex.getDeclaredFields();
            for (Field f : fields){
                s.append("\t");
                // 获取属性的修饰符列表,返回的修饰符是一个数字，每个数字是修饰符的代号
                // 用Modifier类的toString转换成字符串
                s.append(Modifier.toString(f.getModifiers()));
                if (f.getModifiers() != 0) {
                    s.append(" ");
                }
                s.append(f.getType().getSimpleName());
                s.append(" ");
                s.append(f.getName());// 获取属性的名字
                s.append(";\n");

            }
            System.out.println(s);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

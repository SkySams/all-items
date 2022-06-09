package org.example.reflex;

/**
 * @author: zyh
 * @date: 2022/6/9
 */
public class Fanshe {

    public static void main(String[] args) {
        // 第一种创建反射方式; 对象都有了，不需要反射
        MyReflex reflex = new MyReflex();
        Class cla = reflex.getClass();
        System.out.println(cla.getName());

        // 第二种反射方式 依赖太强，不导包就抛编译错误
        Class clas = MyReflex.class;
        System.out.println(clas.getName());
        System.out.println(clas == clas);

        // 第三种反射方式 最常用
        try {
          Class cla3 =  Class.forName("org.example.reflex.MyReflex");
            System.out.println(cla3.getName());
            System.out.println(cla3 == clas);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

}

package org.example.reflex;

import java.util.Arrays;

/**
 * @author: zyh
 * @date: 2022/5/17
 */
public class TestReflexSuperClass {

    public static void main(String[] args) {
        try {
           Class cla = Class.forName("java.lang.String");
           Class superCla = cla.getSuperclass();
           Class[] inface = cla.getInterfaces();
            System.out.println(Arrays.asList(inface));
            System.out.println(superCla.getName());
            System.out.println(superCla.getSimpleName());
            System.out.println(superCla.getCanonicalName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

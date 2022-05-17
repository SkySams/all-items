package org.example.reflex;

import java.util.Date;

/**
 * https://blog.csdn.net/qq_44715943/article/details/120587716
 * @author: zyh
 * @date: 2022/5/17
 */
public class TestReflex {

    /**
     * test
     * @param args
     */
    public static void main(String[] args) {
        try {
           Class c1 = Class.forName("java.lang.String");
           Class c2 = Class.forName("java.lang.Integer");
           Class c3 = Class.forName("java.util.Date");


           String a = "abc";
           Class c4 = a.getClass();
           System.out.println(c4 == c1);

           Date time = new Date();
           Class c5 = time.getClass();
            System.out.println(c5 == c3);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

package org.example.string;

import org.junit.Test;

/**
 * @author: zyh
 * @date: 2023/1/29
 */
public class StringTest {


    @Test
    public void ont_str(){
        String name = "JCccc";
        String nameName = new String("JCccc");
        System.out.println(" == " + name==nameName);
        System.out.println("equals " + name.equals(nameName));
    }


    @Test
    public void two_str(){
        String name1 = "JC";
        String name = "JCccc";
        String nameNew = new String(name1 + "ccc");
        nameNew = nameNew.intern();
        System.out.println(name == nameNew);
    }

    public static void main(String[] args) {
        String name1 = "JCccc";
        String name = "JCccc";
        System.out.println(name == name1);
    }

}

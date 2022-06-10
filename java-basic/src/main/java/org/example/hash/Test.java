package org.example.hash;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

/**
 * @author: zyh
 * @date: 2022/6/10
 */
public class Test {

    public static void main(String[] args)
    {
        Object obj = new Object();
        long address = VM.current().addressOf(obj);
        long hashCode = obj.hashCode();
        System.out.println("GC前-内存地址：" + address);
        System.out.println("GC前-hashcode值：" + hashCode);

        new Object();
        new Object();
        new Object();
        System.gc();

        long afterAddress = VM.current().addressOf(obj);
        long afterHashCode = obj.hashCode();
        System.out.println("GC后-内存地址：" + afterAddress);
        System.out.println("GC后-hashcode值：" + afterHashCode);
        System.out.println("---------------------");

        System.out.println("内存地址 = " + (address == afterAddress));
        System.out.println("hashcode = " + (hashCode == afterHashCode));
        tre();
    }

    public static void tre(){
        // 创建对象并打印JVM中对象的信息
        Object person = new Object();
        System.out.println(ClassLayout.parseInstance(person).toPrintable());

        // 调用hashCode方法，如果重写了hashCode方法则调用System#identityHashCode方法
//        System.out.println(person.hashCode());
         System.out.println(System.identityHashCode(person));

        // 再次打印对象JVM中的信息
        System.out.println(ClassLayout.parseInstance(person).toPrintable());

    }

}

package org.example.design.factory.single;

/**
 * @author: zyh
 * @date: 2022/4/13
 */
public class BMW320 extends BMW{

    /**
     * 具体产品角色：工厂类所创建的对象就是此角色的实例。在java中由一个具体类实现。
     */

    public BMW320(){
        System.out.println("制造-->BMW320");
    }
}

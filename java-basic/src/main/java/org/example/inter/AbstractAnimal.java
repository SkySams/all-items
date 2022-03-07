package org.example.inter;

/**
 * @author: zyh
 * @date: 2022/3/7
 */
public abstract class AbstractAnimal implements Animal{

    @Override
    public void eat(String name) {
        System.out.println(name+"正在吃...");
    }
}

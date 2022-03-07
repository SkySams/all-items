package org.example.inter;

/**
 * @author: zyh
 * @date: 2022/3/7
 */
public class Duck extends AbstractAnimal implements Animal{

    @Override
    public void eat(String name) {
        System.out.println(name+"正在吃...鱼仔");
    }

    public static void main(String[] args) {
        Duck duck = new Duck();
        duck.eat("duck");
    }
}



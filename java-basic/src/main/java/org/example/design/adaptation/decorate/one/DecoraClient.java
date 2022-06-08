package org.example.design.adaptation.decorate.one;

/**
 * @author: zyh
 * @date: 2022/6/8
 */
public class DecoraClient {

    public static void main(String[] args) {
        Human human = new Person();
        Decorator decorator = new Decorator_two(new Decorator_one(new Decorator_zero(human)));
        decorator.wearClothes();

        Person person = null;
        System.out.println(person == null);
    }

}

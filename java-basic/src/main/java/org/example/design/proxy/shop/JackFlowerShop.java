package org.example.design.proxy.shop;

import org.example.design.proxy.people.People;

import java.util.HashSet;
import java.util.Set;

/**
 * jack 花店 人
 * @author: zyh
 * @date: 2022/5/24
 */
public class JackFlowerShop extends FlowerShop{

    private Set<People>  shops = new HashSet<>();

    private People people;

    public JackFlowerShop(People people) {
        this.people = people;
        shops.add(people);
    }

    @Override
    public int peopleSize() {
        return shops.size();
    }

    @Override
    public void floral() {

    }
}

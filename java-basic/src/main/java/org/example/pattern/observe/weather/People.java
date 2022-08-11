package org.example.pattern.observe.weather;

/**
 * 人
 * @author: zyh
 * @date: 2022/8/11
 */
public class People implements ThirdPart{

    private String name;


    public People() {
    }

    public People(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}

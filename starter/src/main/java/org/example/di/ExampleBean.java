package org.example.di;

import java.beans.ConstructorProperties;

/**
 * @author: zyh
 * @date: 2022/5/28
 */
public class ExampleBean {

    @ConstructorProperties({"year", "ultimateAnswer"})
    public ExampleBean(int year, String ultimateAnswer) {
        System.out.println(year);
        System.out.println(ultimateAnswer);
    }
}

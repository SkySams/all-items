package org.example.reflex;

import lombok.Data;

/**
 * @author: zyh
 * @date: 2022/11/29
 */
@Data
public class User {
    public Integer id;
    protected Integer age;
    private String name;
    boolean sex;
    public static final double MATH_PI = 3.1415926D;
}


package org.example.entity;

import io.netty.channel.Channel;

import java.io.Serializable;

/**
 * @author: zyh
 * @date: 2022/3/22
 */
public class Peple implements Serializable {

    private static final long serialVersionUID = 1L;


    private String user;
    private int age;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

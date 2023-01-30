package com.example.basicspring.entity.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author: zyh
 * @date: 2023/1/30
 */
public class Student {

    private Integer id;

    private String name;

    private Integer age;

    /**
     * 入侵式的
     */
//    @JsonProperty("phone")
    private String mobile;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}

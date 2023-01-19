package com.example.basicspring.entity;

import java.io.Serializable;

/**
 * @author: zyh
 * @date: 2023/1/19
 */
public class MUser implements Serializable {

    private Integer id;

    private String name;

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
}

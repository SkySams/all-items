package org.example.controller;

import lombok.Data;

/**
 * @author: zyh
 * @date: 2022/2/22
 */
@Data
public class Respon {

    private String massege;

    private String code;

    private Object data;

    public Respon(String name, String code, Object data) {
        this.massege = name;
        this.code = code;
        this.data = data;
    }
}

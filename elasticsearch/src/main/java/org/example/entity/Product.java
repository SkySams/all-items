package org.example.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * product
 * @author 
 */
@Data
public class Product implements Serializable {

    private Integer id;

    private String name = "";

    private Integer num;

    private static final long serialVersionUID = 1L;

}
package org.example.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * product
 * @author 
 */
@Data
public class Product implements Serializable {

    private Integer id;

    private String name;

    private Integer num;

    private static final long serialVersionUID = 1L;

}
package org.example.seriali;


import lombok.Data;

import java.io.Serializable;

/**
 * @author: zyh
 * @date: 2023/2/6
 */
@Data
public class Dog implements Serializable {

    private static final long serialVersionUID = 2573632430473972862L;
    private String name;
    private Integer age;
    private String nickName;

}

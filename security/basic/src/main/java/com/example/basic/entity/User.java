package com.example.basic.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author: zyh
 * @date: 2022/4/7
 */
@Data
@Entity
@Table(name = "user")
public class User {


    @Id
    private Integer id;

    private String login;

    private String password;

    private String role;

}

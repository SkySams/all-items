package com.example.basicspring.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author: zyh
 * @date: 2023/2/6
 */
@Data
public class Cat {

    @NotNull(message = "缺少 name 名称 信息")
    @NotEmpty( message ="传入的name为空字符串,请传值")
    private String name;

    @NotNull(message = "缺少 age 年龄 信息")
    private Integer age;

    @NotNull(message = "缺少 nickName 昵称 信息")
    @NotEmpty( message ="传入的nickName为空字符串,请传值")
    private String nickName;

}

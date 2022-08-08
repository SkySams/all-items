package org.example.entity.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author: zyh
 * @date: 2022/5/31
 */
@Data
public class Pet {

    @NotNull(message = "不能为空")
    private String ownerId;

    @NotNull(message = "不能为空")
    private String petId;
}

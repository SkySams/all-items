package org.example.entity.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: zyh
 * @date: 2022/6/1
 */
@Data
public class Account {

    @ApiModelProperty("名称")
    private String name;

}

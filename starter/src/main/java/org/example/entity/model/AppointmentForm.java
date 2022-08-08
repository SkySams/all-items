package org.example.entity.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * @author: zyh
 * @date: 2022/5/31
 */
@Data
public class AppointmentForm {

    @NotNull(message = "名称不能为空")
    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "年龄")
    private Integer age;


}

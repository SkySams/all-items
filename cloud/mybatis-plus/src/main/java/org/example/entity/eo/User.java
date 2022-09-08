package org.example.entity.eo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author: zyh
 * @date: 2022/9/8
 */
@Data
@TableName("user")
public class User {

    @TableId
    private Long id;
//    @TableField("nickname")
    private String name;
    private Integer age;
    private String email;

}

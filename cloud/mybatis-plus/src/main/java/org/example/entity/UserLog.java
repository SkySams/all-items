package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * (UserLog)实体类
 *
 * @author makejava
 * @since 2022-10-28 09:42:50
 */
@Data
public class UserLog implements Serializable {
    private static final long serialVersionUID = -60271601307871739L;

    @TableId(type=IdType.AUTO)
    private Integer id;
    
    private Integer userId;




}
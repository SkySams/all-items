package com.example.basicspring.entity;

import lombok.Data;

/**
 * @author: zyh
 * @date: 2023/2/1
 */
@Data
public class Message {

    /**
     * 消息编码
     */
    private String code;

    /**
     * 来自（保证唯一）
     */
    private String form;

    /**
     * 去自（保证唯一）
     */
    private String to;

    /**
     * 内容
     */
    private String content;


}

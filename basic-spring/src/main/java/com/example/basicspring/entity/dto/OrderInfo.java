package com.example.basicspring.entity.dto;

import lombok.Data;

/**
 * springboot 策略模式
 * @author: zyh
 * @date: 2023/2/6
 */
@Data
public class OrderInfo {

    private String orderId;
    private String platFormType;
    private Double amount;
    private String createTime;

}

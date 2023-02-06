package com.example.basicspring.service.impl;

import com.example.basicspring.entity.dto.OrderInfo;
import com.example.basicspring.service.OrderStrategyService;
import com.example.basicspring.util.ConstantUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.example.basicspring.util.ConstantUtil.ORDER_PLAT_FORM_REBATE;

/**
 * @author: zyh
 * @date: 2023/2/6
 * @Description :特殊订单（回扣）
 */
@Slf4j
@Component(ORDER_PLAT_FORM_REBATE)
public class OrderRebate implements OrderStrategyService {
    @Override
    public String preCreateOrder(OrderInfo orderInfo) {
        log.info("***处理国内特殊回扣预下单的相关业务***");
        return orderInfo.getPlatFormType() + "-特殊回扣预下单";
    }
}
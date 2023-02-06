package com.example.basicspring.service.impl;

import com.example.basicspring.entity.dto.OrderInfo;
import com.example.basicspring.service.OrderStrategyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.example.basicspring.util.ConstantUtil.ORDER_PLAT_FORM_DOMESTIC;


/**
 * @author: zyh
 * @date: 2023/2/6
 * @Description: springboot 策略模式
 */
@Slf4j
@Component(ORDER_PLAT_FORM_DOMESTIC)
public class OrderDomestic implements OrderStrategyService {

    /**
     * 国内预下单
     *
     * @param orderInfo
     * @return
     */
    @Override
    public String preCreateOrder(OrderInfo orderInfo) {

        log.info("*处理国内预下单的相关业务*");
        return orderInfo.getPlatFormType()+"-国内预下单";
    }
}

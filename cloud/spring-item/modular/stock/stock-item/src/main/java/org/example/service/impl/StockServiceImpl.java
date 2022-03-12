package org.example.service.impl;

import org.apache.dubbo.config.annotation.DubboService;
import org.example.service.StockSevice;

/**
 * @author MK
 */
@DubboService
public class StockServiceImpl implements StockSevice {

    @Override
    public String stock() {
        return "stock hello world";
    }
}

package org.example.service;

import org.example.Order;

/**
 * @author: zyh
 * @date: 2023/3/10
 */
public interface OrderService {

    Order create(String userId, String commodityCode, int orderCount);

}

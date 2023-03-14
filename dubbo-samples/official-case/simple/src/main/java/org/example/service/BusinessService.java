package org.example.service;

/**
 * @author: zyh
 * @date: 2023/3/14
 */
public interface BusinessService {

    /**
     * 用户订购商品
     *
     * @param userId        用户ID
     * @param commodityCode 商品编号
     * @param orderCount    订购数量
     */
    void purchase(String userId, String commodityCode, int orderCount);

}

package org.example.order.provider;

import org.example.order.entity.SmsAdvert;
import org.example.order.service.SmsAdvertService;
import org.example.order.service.SmsService;
import org.example.service.ShoppingOrderService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 *
 * RandomLoadBalance	加权随机	默认算法，默认权重相同
 * RoundRobinLoadBalance	加权轮询	借鉴于 Nginx 的平滑加权轮询算法，默认权重相同，
 * LeastActiveLoadBalance	最少活跃优先 + 加权随机	背后是能者多劳的思想
 * ShortestResponseLoadBalance	最短响应优先 + 加权随机	更加关注响应速度
 * ConsistentHashLoadBalance	一致性 Hash	确定的入参，确定的提供者，适用于有状态请求
 *
 * @author: zyh
 * @date: 2022/3/10
 */
@DubboService(loadbalance = "roundrobin",timeout = 10000)
public class ShoppingOrderServiceImpl implements ShoppingOrderService {

    @Value("${server.port}")
    private int port;

    @Autowired
    private SmsService service;

    @Autowired
    private SmsAdvertService smsAdvertService;

    @Override
    public String add() {
        service.selectAll();
        return "nice"+port;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createOrder() {
        SmsAdvert smsAdvert = new SmsAdvert();
        Date date = new Date();
        smsAdvert.setGmtCreate(date);
        smsAdvert.setBeginTime(date);
        smsAdvert.setEndTime(date);
        smsAdvert.setPicUrl("url");
        smsAdvert.setRemark("remark");
        smsAdvert.setSort(1);
        smsAdvert.setStatus(1);
        smsAdvert.setTitle("title");
        smsAdvertService.save(smsAdvert);
    }


}

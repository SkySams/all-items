package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ScheduledMessage;
import org.example.service.JmsMessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.JmsProperties;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: zyh
 * @date: 2022/3/8
 */
@Slf4j
@Service("jmsMessagingService")
public class JmsMessagingServiceImpl implements JmsMessagingService {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    /**
     * 发送消息
     * 发送消息，destination是发送到的队列，message是待发送的消息
     *
     * @param destination
     * @param message
     */
    @Override
    public void sendMessage(Destination destination, String message) {
        log.info("发送信息：{}",message);
        jmsMessagingTemplate.convertAndSend(destination, message);
    }

    /**
     * 延迟发送
     *
     * @param destination
     * @param message
     */
    @Override
    public void sendMessage(Destination destination, String message, long time) {
        log.info("延迟发送信息：{}",message);
        Map<String, Object> map = new HashMap<>();
        map.put(ScheduledMessage.AMQ_SCHEDULER_MANAGEMENT_DESTINATION, time);
        jmsMessagingTemplate.convertAndSend(destination, message, map);
    }



}

package org.example.service;

import javax.jms.Destination;

/**
 * @author: zyh
 * @date: 2022/3/8
 */
public interface JmsMessagingService {

    /**
     * 发送消息
     * @param destination
     * @param message
     */
    void sendMessage(Destination destination, final String message);

}

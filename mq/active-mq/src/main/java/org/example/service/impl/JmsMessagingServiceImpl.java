package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ScheduledMessage;
import org.example.service.JmsMessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.JmsProperties;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
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

    private final JmsMessagingTemplate jmsMessagingTemplate;

    private final JmsTemplate jmsQueueTemplate;

    public JmsMessagingServiceImpl(JmsMessagingTemplate jmsMessagingTemplate, JmsTemplate jmsQueueTemplate) {
        this.jmsMessagingTemplate = jmsMessagingTemplate;
        this.jmsQueueTemplate = jmsQueueTemplate;
    }

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

//        jmsQueueTemplate.send("active.queue", session -> {
//            TextMessage textMessage = session.createTextMessage(message);
//            textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, time * 100);
//            return textMessage;
//        });

//        Map<String, Object> map = new HashMap<>();
//        map.put(ScheduledMessage.AMQ_SCHEDULER_MANAGEMENT_DESTINATION, time);
//        jmsMessagingTemplate.convertAndSend(destination, message, map);


        this.delaySend(destination,message,time);
    }

    /**
     * 延时发送
     *
     * @param destination 发送的队列
     * @param data        发送的消息
     * @param time        延迟时间
     */
    public <T extends Serializable> void delaySend(Destination destination, T data, Long time) {
        Connection connection = null;
        Session session = null;
        MessageProducer producer = null;
        // 获取连接工厂
        ConnectionFactory connectionFactory = jmsMessagingTemplate.getConnectionFactory();
        try {
            // 获取连接
            connection = connectionFactory.createConnection();
            connection.start();
            // 获取session，true开启事务，false关闭事务
            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            // 创建一个消息队列
            producer = session.createProducer(destination);
            producer.setDeliveryMode(JmsProperties.DeliveryMode.PERSISTENT.getValue());
            ObjectMessage message = session.createObjectMessage(data);
            //设置延迟时间
            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, time);
            // 发送消息
            producer.send(message);
            log.info("发送消息：{}", data);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (producer != null) {
                    producer.close();
                }
                if (session != null) {
                    session.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}

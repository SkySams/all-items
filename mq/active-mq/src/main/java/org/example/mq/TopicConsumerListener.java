package org.example.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author: zyh
 * @date: 2022/3/8
 */
@Slf4j
@Component
public class TopicConsumerListener {

    /**
     * topic模式的消费者
     * @param message
     */
    @JmsListener(destination="${spring.activemq.topic-name}", containerFactory="topicListener")
    public void readActiveQueue(String message) {
        log.info("topic接受到：{}", message);
    }

}

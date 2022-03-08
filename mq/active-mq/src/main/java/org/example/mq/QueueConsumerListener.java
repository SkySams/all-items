package org.example.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author: zyh
 * @date: 2022/3/8
 */
@Slf4j
@Component
public class QueueConsumerListener {


    /**
     * queue模式 消费者
     * @param message
     */
    @JmsListener(destination="${spring.activemq.queue-name}", containerFactory="queueListener")
    public void readActiveQueue(String message) {
        log.info("queue接受到: {}", message);
    }

}

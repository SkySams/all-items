package org.example.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * @author: zyh
 * @date: 2022/3/8
 */
@Slf4j
@Component
public class QueueConsumerListener {

    private ExecutorService threadPool= new ThreadPoolExecutor(6, Integer.MAX_VALUE,
            60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

    /**
     * queue模式 消费者
     * @param message
     */
    @JmsListener(destination="${spring.activemq.queue-name}", containerFactory="queueListener")
    public void readActiveQueue(String message) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                log.info(Thread.currentThread().getName()+" queue接受到: {}", message);
            }
        });

    }

}

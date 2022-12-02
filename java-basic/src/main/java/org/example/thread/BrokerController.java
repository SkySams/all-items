package org.example.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author: zyh
 * @date: 2022/11/24
 */
public class BrokerController {

    ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor(new ThreadFactoryImpl(
            "BrokerControllerScheduledThread"));


}

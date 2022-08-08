package org.example.service.impl.async;

import lombok.extern.slf4j.Slf4j;
import org.example.service.async.AsyncService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * @author: zyh
 * @date: 2022/8/8
 */
@Slf4j
@Service("asyncService")
public class AsyncServiceImpl implements AsyncService {

    /**
     * asyncThreadPoolTaskExecutor 自定义线程池
     */
    @Async("asyncThreadPoolTaskExecutor")
    @Override
    public void wocao() {
        for (int i = 0; i < 1000000; i++){
            String threadName = Thread.currentThread().getName();
            System.out.println("当前线程："+threadName+" 异步请求 ------------------------------> :"+i);
        }
    }

    @Async
    @Override
    public Future<String> asyncMethod() {
        sleet();
        log.info("当前线程名称：{}", Thread.currentThread().getName());
        return new AsyncResult<>("hello async");
    }

    private void sleet()   {
        try {
            Thread.sleep(10);
        }catch (InterruptedException e){

        }
    }
}

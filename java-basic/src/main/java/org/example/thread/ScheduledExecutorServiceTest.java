package org.example.thread;

import cn.hutool.core.date.DateUtil;
import org.junit.Test;

import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * @author: zyh
 * @date: 2022/11/1
 */
public class ScheduledExecutorServiceTest {

    @Test
    public void testScheduleService(){
//        ScheduledExecutorService scheduledExecutorService =
//                Executors.newSingleThreadScheduledExecutor(new ThreadFactoryImpl("TestScheduledThread"));
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        System.out.println(DateUtil.now());
        /**
         * 延迟消息
         */
//        scheduledExecutorService.schedule(new Runnable() {
//            @Override
//            public void run() {
//                Thread thread = Thread.currentThread();
//                System.out.println("我是间隔1s执行的任务，线程name："+ thread.getName() + ",执行时间戳：" + DateUtil.now());
//            }
//        }, 40, TimeUnit.SECONDS);

        scheduledExecutorService.scheduleAtFixedRate(() -> {
            Thread thread = Thread.currentThread();
            System.out.println("我是间隔1s执行的任务，线程name："+ thread.getName() + ",执行时间戳：" + DateUtil.now());
        },0L,1, TimeUnit.SECONDS);

         scheduledExecutorService.scheduleAtFixedRate(() -> {
             try {
                 Thread thread = Thread.currentThread();
                 System.out.println("我是间隔5s执行的任务，线程name："+ thread.getName() + ",执行时间戳：" + DateUtil.now());
                 // 如果抛出异常将不会继续下去
//                 throw new RuntimeException();
             }catch (Exception e){
                 System.out.println(e);//模拟输出 error 信息
             }
         },3L,5, TimeUnit.SECONDS);



        while (true){}

    }

}


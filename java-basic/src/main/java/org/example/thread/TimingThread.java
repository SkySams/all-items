package org.example.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.*;

/**
 * @author: zyh
 * @date: 2022/10/31
 */
public class TimingThread {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor(new ThreadFactoryImpl(
                "TestScheduledThread"));

        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Thread thread = Thread.currentThread();
                System.out.println("我是间隔5s执行的任务，线程name："+ thread.getName() + ",执行时间戳：" + System.currentTimeMillis() / 1000);
            }
        }, 0L, 5, TimeUnit.SECONDS);

        ScheduledFuture scheduledFuture = scheduledExecutorService.schedule(new Callable() {
            @Override
            public Integer call() {
                Thread thread = Thread.currentThread();
                System.out.println("我是延迟10s执行的一次性任务，线程name："+ thread.getName() + ",执行时间戳：" + System.currentTimeMillis() / 1000);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return 250;
            }
        }, 10L,  TimeUnit.SECONDS);
        // scheduledFuture.get() 会阻塞调用方线程，这里是 main 线程
        System.out.println("scheduledFuture.get():" + scheduledFuture.get());
        System.out.println("hello world");
    }


}

class MyTimerTask extends TimerTask {
    @Override
    public void run() {
        System.out.println(new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date()));
    }
}



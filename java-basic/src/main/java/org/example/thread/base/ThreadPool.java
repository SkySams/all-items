package org.example.thread.base;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * https://blog.csdn.net/weixin_45033015/article/details/126170165
 * @author: zyh
 * @date: 2022/11/1
 */
public class ThreadPool {

    @Test
    public void testThreadPool(){
        ExecutorService es = Executors.newFixedThreadPool(12);
        es.execute(new MyRunable2());
        es.execute(new MyRunable());
        es.execute(new MyRunable2());

        es.submit(new MyCallable2());
        es.submit(new MyCallable());

        es.shutdown();
    }

}


class MyRunable2 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " ---> " + i);
        }
    }
}

class MyCallable2 implements Callable {

    @Override
    public Object call() throws Exception {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " ---> " + i);
        }
        return "success";
    }
}




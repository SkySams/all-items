package org.example.thread.base;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author: zyh
 * @date: 2022/11/1
 */
public class CallableFutureTask {
    /**
        1、实现Callable接口并重写object call()方法
        2、实现FutureTask类包装 Callable 对象，该FutureFask 对象封装了 Callable 对象 call()方法分返回值
        3、实现FutureTask对象作为Thread 对象的入参创建并启动线程（因为FutureTask实现Runable接口）
        4、调用FutureTask的get()方法可以获取线程结束后的返回值


     */

    @Test
    public void testCallable() throws ExecutionException, InterruptedException {
        FutureTask task = new FutureTask(new MyCallable());
        Thread thread = new Thread(task);
        thread.start();

        System.out.println("FutureTask 返回值："+task.get());
    }

    @Test
    public void testCallableLambda() throws ExecutionException, InterruptedException {
        FutureTask task = new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(1000*2);
                return 1+3;
            }
        });

        Thread thread = new Thread(task);
        thread.setName("好厉害的线程");
        thread.start();

        System.out.println("FutureTask 返回值："+task.get());

    }

}

class MyCallable implements Callable{

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Object call() throws Exception {
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(1000*3);
        return 1+2;
    }
}
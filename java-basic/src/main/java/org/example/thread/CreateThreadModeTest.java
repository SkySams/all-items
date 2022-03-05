package org.example.thread;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * 创建线程有几种方式
 *
 * @author: zyh
 * @date: 2022/3/5
 */
public class CreateThreadModeTest {

    /**
     * 创建线程方式1
     */
    @Test
    public void createModeOne() {
        OneCreateThreadMode oneMode = new OneCreateThreadMode();
        oneMode.setName("线程1");
        oneMode.start();
    }

    @Test
    public void createModelTwo(){
        Thread twoMode = new Thread(new TwoCreateThreadMode());
        twoMode.setName("线程2");
        twoMode.start();
    }

    @Test
    public void createModeThree() throws ExecutionException, InterruptedException {
        ThreeCreateThreadMode threeMode = new ThreeCreateThreadMode();
        FutureTask<Integer> task = new FutureTask<>(threeMode);
        new Thread(task).start();
        System.out.println(task.get());
    }

    @Test
    public void createModeFour(){
        FourCreateThreadMode fourCreateThreadMode = new FourCreateThreadMode();
        ExecutorService executorService = fourCreateThreadMode.getExecotorService();
        executorService.submit(new OneCreateThreadMode());
        executorService.shutdown();
    }

}

/**
 * 第一种创建线程方式：继承Thread
 * <p>
 * 重写run()方法
 */
class OneCreateThreadMode extends Thread {

    /**
     * If this thread was constructed using a separate
     * <code>Runnable</code> run object, then that
     * <code>Runnable</code> object's <code>run</code> method is called;
     * otherwise, this method does nothing and returns.
     * <p>
     * Subclasses of <code>Thread</code> should override this method.
     *
     * @see #start()
     * @see #stop()
     * //     * @see #Thread(ThreadGroup, Runnable, String)
     */
    @Override
    public void run() {
        this.execute();
    }

    /**
     * 执行
     */
    public void execute() {
        System.out.println(Thread.currentThread().getName() + ":" + "execute");
    }
}

/**
 * 第二种创建线程方式：实现 #Runable 类
 */
class TwoCreateThreadMode implements Runnable {

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        this.execute();
    }

    public void execute() {
        System.out.println(Thread.currentThread().getName() + ":" + "execute2");
    }
}

/**
 * 第三种创建线程方式： 实现Callable 类
 * 重写call方法
 */
class ThreeCreateThreadMode implements Callable<Integer> {

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Integer call() throws Exception {
        this.execute();
        int sum = 0;
        int size = 10;
        for (int i = 0; i < size; i++) {
            sum++;
        }
        return sum;
    }

    public void execute() {
        System.out.println(Thread.currentThread().getName() + ":" + "execute3");
    }
}

/**
 * 第四种创建线程方式：创建线程池 ExecutorService
 */
class FourCreateThreadMode {

    public ExecutorService getExecotorService(){
        return Executors.newSingleThreadExecutor();
    }

}

package org.example.thread;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
    public void createModelTwo() {
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
    public void createModeFour() {
        FourCreateThreadMode fourCreateThreadMode = new FourCreateThreadMode();
        ExecutorService executorService = fourCreateThreadMode.getExecotorService();
        executorService.submit(new OneCreateThreadMode());
        executorService.shutdown();
    }

    @Test
    public void createThreadPoolOneMode() throws InterruptedException {
        FourCreateThreadMode fourCreateThreadMode = new FourCreateThreadMode();
        /**
         * 线程池大小为 10
         */
        ScheduledExecutorService scheduledExecutorService = fourCreateThreadMode.newScheduledThreadPool(10);
        // 延迟2秒执行
//        scheduledExecutorService.schedule(new OneCreateThreadMode(), 2, TimeUnit.SECONDS);
        // 延迟1秒执行， 每2秒执行一次
        scheduledExecutorService.scheduleAtFixedRate(new OneCreateThreadMode(),1,2,TimeUnit.SECONDS);
        Thread.sleep(5000);

    }

    @Test
    public void createThreadPoolTwoMode() throws ExecutionException, InterruptedException {
        FourCreateThreadMode fourCreateThreadMode = new FourCreateThreadMode();
        ExecutorService executorService = fourCreateThreadMode.getExecotorService();
        Collection<ThreeCreateThreadMode> collection = new ArrayList<>();
        collection.add(new ThreeCreateThreadMode());
        collection.add(new ThreeCreateThreadMode());
        collection.add(new ThreeCreateThreadMode());
        collection.add(new ThreeCreateThreadMode());
        collection.add(new ThreeCreateThreadMode());
        collection.add(new ThreeCreateThreadMode());
        executorService.invokeAny(collection);
    }

}

/**
 * 第一种创建线程方式：继承Thread
 * <p>
 * 重写run()方法
 * <p>
 * 缺点：
 * 1、每次都创建线程对象性能差
 * 2、线程缺乏统一管理，可能无线创建线程，相互之间竞争，即可能占用过多系统资源导致死机或oom（内存用完了 Out Of Memory）
 * 3、缺乏更多功能: 如定时执行、定期执行、线程中断
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
 * <p>
 * 线程池优点：
 * 1. 重用存在的线程，减少对象创建、消亡的开销，性能佳。
 * 2. 可有效控制最大并发线程数，提高系统资源的使用率，同时避免过多资源竞争，避免堵塞。
 * 3. 提供定时执行、定期执行、单线程、并发数控制等功能。
 */
class FourCreateThreadMode {

    public ExecutorService getExecotorService() {
        return Executors.newSingleThreadExecutor();
    }

    /**
     * 可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
     * 线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。
     *
     * @return
     */
    public ExecutorService newCachedThreadPool() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        return executorService;
    }

    /**
     * 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
     *
     * @param size 线程池大小
     * @return
     */
    public ExecutorService newFixedThreadPool(int size) {
        ExecutorService executorService = Executors.newFixedThreadPool(size);
        return executorService;
    }

    /**
     * 创建一个定长线程池，支持定时及周期性任务执行
     *
     * @param size
     * @return
     */
    public ScheduledExecutorService newScheduledThreadPool(int size) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(size);
        return scheduledExecutorService;
    }

    /**
     * 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
     *
     * 结果依次输出，相当于顺序执行各个任务。
     * 现行大多数GUI程序都是单线程的。
     * Android中单线程可用于数据库操作，文件操作，应用批量安装，应用批量删除等不适合并发但可能IO阻塞性及影响UI线程响应的操作
     *
     * @return
     */
    public ExecutorService newSingleThreadExecutor() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        return executorService;
    }


}

/**
 * 线程池不允许使用Executors去创建，
 * 而是通过ThreadPoolExecutor的方式，
 * 这样的处理方式让写的同学更加明确线程池的运行规则，
 * 规避资源耗尽的风险。
 *
 * 说明：Executors 返回的线程池对象的弊端如下：
 * 1、FixedThreadPool和SingleThreadPool:
 *  允许的请求队列长度为Integer.MAX_VALUE，可能会堆积大量的请求，从而导致OOM。
 * 2、CachedThreadPool:
 *  允许的创建线程数量为Integer.MAX_VALUE，可能会创建大量的线程，从而导致OOM。
 */
class FiveCreateThreadMode {

    /**
     * 通过ThreadPoolExecutor创建
     * @return
     */
    public static ThreadPoolExecutor createThreadPoolExecutor(){
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
    }


    public static ExecutorService newFixedThreadPool(int nThreads) {
        return new ThreadPoolExecutor(nThreads, nThreads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
    }


}
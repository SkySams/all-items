package org.example.thread.base;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 继承Thread类。重写run方法
 * @author: zyh
 * @date: 2022/11/1
 */
public class ThreadOne {

    @Test
    public void testMyThread(){
        ThreandParam thread1 = new ThreandParam("线程1","www.baidu.com");
        thread1.start();
        ThreandParam thread2=  new ThreandParam("线程2", "www.bilibili.com");
        thread2.start();
    }

    /**
     * 主线程
     * @param args
     */
    // 注意：main方法中的代码运行在主线程(主栈)中；分支线程对象中的代码运行在分支线程（分支栈）中
    public static void main(String[] args){

        // 创建一个分支线程对象
        MyClass myClass1 = new MyClass();
        // 开辟新的栈空间，启动分支线程（jvm 自动调用 run() 方法）
        myClass1.start();

        // 这里的代码还是运行在主线程(主栈)中
        for (int i =0; i < 1000; i++){
            System.out.println("主线程："+i);
        }
    }

    @Test
    public void testThreadJoin() throws InterruptedException {
        Thread thread = new Thread( () ->{
           for (int i = 0; i < 10000; i++){
               System.out.println(Thread.currentThread().getName()+": "+i);
           }
        });
        thread.start();
        // 等待线程执行完了，再执行主线程
        thread.join();

        System.out.println("Main~");
    }


    /**
     * 将线程改成守护线程
     */
    @Test
    public void testThreadConservation(){
        Thread thread = new Thread(() -> {
            while (true){
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.setName("=== Backup Thread ===");
        //  设置当前线程为 守护线程
        thread.setDaemon(true);
        thread.start();

        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " ---> " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    // 定时线程
    @Test
    public void schedule(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date()));
            }
        },3,1000*3);
        while (true){}
    }


}

class MyTimerTask extends TimerTask{

    @Override
    public void run() {
        System.out.println(new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date()));
    }
}


class MyClass extends Thread{

    @Override
    public void run() {
        for (int i=0; i < 1000; i++){
            System.out.println("分支线程:"+i);
        }
    }
}


/**
 * 不同的线程做不同的请求
 */
class ThreandParam extends  Thread{


    String url;

    /**
     * 每条线程执行不同的url请求
     * @param url
     */
    public ThreandParam(String url){
        this.url = url;
    }

    public ThreandParam(String name, String url) {
        super(name);
        this.url = url;
    }

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
     * @see Thread #(ThreadGroup, Runnable, String)
     */
    @Override
    public void run() {
        super.run();
        this.method(this.url);
    }

    public void method(String url){
//      在线程中，每次请求不同的url 怎办呢
        System.out.println("当前线程"+Thread.currentThread().getName()+"请求url:"+url);
    }

}

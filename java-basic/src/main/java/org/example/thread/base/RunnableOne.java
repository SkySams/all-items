package org.example.thread.base;

import org.junit.Test;

/**
 * @author: zyh
 * @date: 2022/11/1
 */
public class RunnableOne {

    @Test
    public void testRunableOne(){
        Thread thread = new Thread(new MyRunable());
        thread.setName("MyThread1");
        thread.start();
    }

    @Test
    public void testRunnableTwo(){
      Thread thread =  new Thread(new Runnable() {
           @Override
           public void run() {
               for (int i=0; i<1000; i++){
                   System.out.println("分支线程: "+i);
               }
           }
       });
      thread.start();
    }

    /**
     * lambda
     */
    @Test
    public void testRunableLambda(){
        Thread thread = new Thread(()->{
            for(int i=0; i<1000; i++){
                System.out.println(Thread.currentThread().getName()+"："+i);
            }
        });
        thread.setName("线程2000");
        thread.start();
    }

}

/**
 * 实现Runable 类型
 */
class MyRunable implements Runnable {


    @Override
    public void run() {
        for (int i=0;i<1000;i++){
            System.out.println(Thread.currentThread().getName()+"分支线程："+i);
        }
    }
}


package org.example.thread.sleep;

import lombok.SneakyThrows;

/**
 * @author: zyh
 * @date: 2023/2/1
 */
public class SleepTest implements Runnable{

    private int count = 1;

    @SneakyThrows
    @Override
    public void run() {
        synctest();
//        while (true){
//            synchronized (this){
//                System.out.println("开始了" + Thread.currentThread().getName() + " count=" + count);
//                if (count < 10) {
//                    Thread.sleep(1000);
//                }
//                System.out.println("睡醒了" + Thread.currentThread().getName() + " count=" + count);
//
//                count++;
//                if (count > 10) {
//                    break;
//                }
//                System.out.println("这时候执行完一轮，即将会释放锁线程名称：" + Thread.currentThread().getName());
//            }
//        }

       // version 1
       /* while (true){
            if (count < 10){
                Thread.sleep(100);
            }
            System.out.println(Thread.currentThread().getName() + " count=" + count);
            count++;
            if (count > 10){
                break;
            }
        }*/
    }

    private synchronized void synctest() throws InterruptedException {

        System.out.println(Thread.currentThread().getName()+"拿到锁！！！！！！！！！！");
        while (true) {

            System.out.println("开始了" + Thread.currentThread().getName() + "count=" + count);
            if (count < 10) {
                Thread.sleep(100);
            }
            System.out.println("睡醒了" + Thread.currentThread().getName() + "count=" + count);

            count++;
            if (count > 10) {
                System.out.println(Thread.currentThread().getName()+"大过10了，要出去了");
                break;
            }

        }
        System.out.println(Thread.currentThread().getName()+"while方法执行完了，把锁释放给别人");

    }



}

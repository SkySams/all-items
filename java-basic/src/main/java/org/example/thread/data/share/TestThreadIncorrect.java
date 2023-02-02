package org.example.thread.data.share;

import lombok.SneakyThrows;

/**
 * @author: zyh
 * @date: 2023/2/1
 */
public class TestThreadIncorrect  extends Thread{

    private int watermelon = 10;


    @SneakyThrows
    @Override
    public void run() {
        for (int i =0; i < 10; i++){
            // 如果不加上 synchronized ，会存在数据同步的问题
            synchronized (this){
                if (watermelon > 0){
                    System.out.println(Thread.currentThread().getName()+  " 正在运行， 剩下的数量 " +watermelon--);
                }
            }

        }
    }

    public static void main(String[] args) {
        //  资源不共享
//        new TestThreadIncorrect().start();
//        new TestThreadIncorrect().start();

        // 共享同一份数据
        TestThreadIncorrect incorrect =  new TestThreadIncorrect();
        new Thread(incorrect).start();
        new Thread(incorrect).start();
    }

}

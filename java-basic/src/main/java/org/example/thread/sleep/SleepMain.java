package org.example.thread.sleep;

import org.junit.Test;

/**
 * @author: zyh
 * @date: 2023/2/1
 */
public class SleepMain {

    public static void main(String[] args) {
        SleepTest sleepTest=new SleepTest();
        Thread thread1=new Thread(sleepTest);
        Thread thread2=new Thread(sleepTest);
        thread1.start();
        thread2.start();
    }

}

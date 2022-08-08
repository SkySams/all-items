package org.example.pattern.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: zyh
 * @date: 2022/8/8
 */
@Slf4j
public class Worker implements Runnable{


    private final Task task;

    public Worker(final Task task) {
        this.task = task;
    }

    @Override
    public void run() {
//        log.info("{} processing {}", Thread.currentThread().getName(), task.toString());
        try {
            System.out.println(Thread.currentThread().getName() + " processing " + task.toString());
            Thread.sleep(task.getTimeMs());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

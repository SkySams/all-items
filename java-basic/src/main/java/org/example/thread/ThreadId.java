package org.example.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: zyh
 * @date: 2022/4/16
 */
public class ThreadId {

    private static final AtomicInteger nextId = new AtomicInteger(0);

    // Thread local variable containing each thread's ID
    private static final ThreadLocal<Integer> threadId =
            new ThreadLocal<Integer>() {
                @Override
                protected Integer initialValue() {
                    return nextId.getAndIncrement();
                }
            };

    // Returns the current thread's unique ID, assigning it if necessary
    public static int get() {
        return threadId.get();
    }

    public static void main(String[] args) {
        System.out.println(ThreadId.get());
    }

}

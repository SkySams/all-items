package org.example.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: zyh
 * @date: 2022/4/16
 */
public class ThreadId {

    private static final AtomicInteger nextId = new AtomicInteger(100);

    private static final ThreadLocal<Integer> threadId = ThreadLocal.withInitial(() -> nextId.getAndIncrement());

    public static int get() {
        return threadId.get();
    }

    public static void main(String[] args) {
        System.out.println(ThreadId.get());
    }

}

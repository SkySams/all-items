package com.example.basicspring.controller;

import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author: zyh
 * @date: 2023/1/19
 */
@RestController
public class RedistController {

    @Autowired
    private RedissonClient redisson;


    /**
     * 闭锁（CountDownLatch）
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/lockSchool")
    public String lockSchool() throws InterruptedException {
        // 按名称返回 countDownLatch 实例。
        RCountDownLatch latch = redisson.getCountDownLatch("anyCountDownLatch");
        // 仅当先前的计数已达到零或根本未设置时才设置新的计数值。
        latch.trySetCount(5);
        // 等到计数器达到零。
        latch.await();
        return "放学啦";
    }

    /**
     * 闭锁（CountDownLatch）
     * @return
     */
    @GetMapping("/afterSchool")
    public String afterSchool() {
        // 按名称返回 countDownLatch 实例。
        RCountDownLatch latch = redisson.getCountDownLatch("anyCountDownLatch");
        // 减少锁存器的计数器。当计数达到零时通知所有等待线程。
        latch.countDown();
        return "走了一个班";
    }

    /**
     * 信号量（Semaphore）
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/park")
    public String park() throws InterruptedException {
        // 按名称返回信号量实例
        RSemaphore semaphore = redisson.getSemaphore("semaphore");
        // 获得许可。如有必要，等待许可证可用。
        semaphore.acquire();
        return "park";
    }

    @GetMapping("/go")
    public String go() {
        // 按名称返回信号量实例
        RSemaphore semaphore = redisson.getSemaphore("semaphore");
        // 发放许可证。增加可用许可证的数量。
        semaphore.release();
        return "go";
    }

    @GetMapping("/read")
    public String readValue() {
        RReadWriteLock rwlock = redisson.getReadWriteLock("anyRWLock");
        // 最常见的使用方法
        RLock rLock = rwlock.readLock();
        // 上锁
        rLock.lock();
        System.out.println("读锁" + Thread.currentThread().getId());

        String uuid = "";
        try {
//            uuid = stringRedisTemplate.opsForValue().get("key");
            // 业务
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("解锁" + Thread.currentThread().getId());
            // 解锁
            rLock.unlock();
        }
        return uuid;
    }

    @GetMapping("/write")
    public String writeValue() {
        RReadWriteLock rwlock = redisson.getReadWriteLock("anyRWLock");
        // 最常见的使用方法
        RLock rLock = rwlock.writeLock();
        // 上锁
        rLock.lock();
        System.out.println("写锁" + Thread.currentThread().getId());

        String uuid = "";
        try {
//            uuid = UUID.fastUUID().toString();
//            stringRedisTemplate.opsForValue().set("key", uuid);
            // 业务
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("解锁" + Thread.currentThread().getId());
            // 解锁
            rLock.unlock();
        }
        return uuid;
    }

    /**
     * 可重入锁（Reentrant Lock）
     * 另外Redisson还通过加锁的方法提供了leaseTime的参数来指定加锁的时间。超过这个时间后锁便自动解开了。
     * @return
     */
    @GetMapping("/hello")
    public String hello() throws InterruptedException {
        RLock lock = redisson.getLock("anyLock");
        // 尝试加锁，最多等待100秒，上锁以后10秒自动解锁
        boolean res = lock.tryLock(100, 10, TimeUnit.SECONDS);
        if (res) {
            System.out.println("获取锁--" + Thread.currentThread().getId());
            try {
                System.out.println("业务--" + Thread.currentThread().getId());
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("解锁--" + Thread.currentThread().getId());
                lock.unlock();
            }
        }
        return "hello";
    }





}

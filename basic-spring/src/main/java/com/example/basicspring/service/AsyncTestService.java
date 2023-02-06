package com.example.basicspring.service;

import java.io.IOException;

/**
 * @author: zyh
 * @date: 2023/2/6
 */
public interface AsyncTestService {

    /**
      这里将会在impl里标注为异步任务，在执行此方法的时候，会单独开启线程来执行
     */
    void function1() throws InterruptedException;

    void function2();

    /**
     * Springboot 指定重发的次数和延迟时间，定时异步执行 重发任务
     * @param taskDelayMill
     * @return
     * @throws InterruptedException
     * @throws IOException
     */
    String testNotice(int[] taskDelayMill) throws InterruptedException, IOException;

}

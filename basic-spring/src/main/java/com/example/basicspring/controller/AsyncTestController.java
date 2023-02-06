package com.example.basicspring.controller;

import com.example.basicspring.service.AsyncTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zyh
 * @date: 2023/2/6
 */
@Slf4j
@RestController
public class AsyncTestController {


    @Autowired
    AsyncTestService asyncTestService;


    @GetMapping("/async/test")
    public void test() throws InterruptedException {
//        for (int i = 0; i < 10; i++) {
//            asyncTestService.function1(); // 执行异步任务
//            asyncTestService.function2();
//        }

        asyncTestService.function1(); // 执行异步任务
        asyncTestService.function2();

    }

    @GetMapping("/testAsyncNotice")
    public void testAsyncNotice() throws Exception {
        log.info("发货通知调用开始！");
        int[] taskArrays = new int[]{2000, 5000, 10000};
        asyncTestService.testNotice(taskArrays);
        log.info("已经开始通知,异步执行通知");
    }


}

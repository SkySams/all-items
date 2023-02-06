package com.example.basicspring.service.impl;

import com.example.basicspring.element.DelayElement;
import com.example.basicspring.service.AsyncTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.DelayQueue;

/**
 * @author: zyh
 * @date: 2023/2/6
 */
@Slf4j
@Service
public class AsyncTestServiceImpl implements AsyncTestService {
    /**
     * 这里进行标注为异步任务，在执行此方法的时候，会单独开启线程来执行
     */
    @Async("getExecutor")
    @Override
    public void function1() throws InterruptedException {
        log.info("f1 : " + Thread.currentThread().getName() + "   " + UUID.randomUUID());
//        try {
//            Thread.sleep(10000);
//            System.out.println("EEEE");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        //故意等10秒，那么异步线程开起来，这样明显看到 2方法不用等1方法执行完再调用了
        Thread.sleep(10000);
        log.info("f1 : {}", Thread.currentThread().getName() + "   " + "EEEE");

    }

    @Async("getExecutor")
    @Override
    public void function2() {
        log.info("f2 : " + Thread.currentThread().getName() + "   " + UUID.randomUUID());
        try {
            Thread.sleep(100);
            log.info("f2 : {}", Thread.currentThread().getName() + "   " + "AAAA");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Springboot 指定重发的次数和延迟时间，定时异步执行 重发任务
     *
     * @param taskDelayMill
     * @return
     * @throws InterruptedException
     * @throws IOException
     */
    @Async("getExecutor")
    @Override
    public String testNotice(int[] taskDelayMill) throws InterruptedException, IOException {
        log.info(Thread.currentThread().getName() + " : {}", "   -------正在异步执行任务------" + new Date());
        DelayQueue delayQueue = new DelayQueue();
        //数组的length大小就是额外需要发送的通知数
        int taskSum = taskDelayMill.length;

        for (int i = 0; i < taskSum; i++) {
            delayQueue.put(new DelayElement(taskDelayMill[i]));
        }

        System.out.println("开始时间：" + DateFormat.getDateTimeInstance().format(new Date()));
        while (!delayQueue.isEmpty()) {
            // 执行延迟任务
            System.out.println("现在执行延迟任务,调用业务接口");

            //模拟调用API,通知发货,得到发货结果 成功或失败

            String result = getNoticeResult();

            System.out.println("通知发货的结果是：" + result);
            if (!result.equals("success")) {

                System.out.println("任务执行中：" + delayQueue.take());
            } else {

                break;
            }
        }
        //查询订单结果

        System.out.println("通知任务不需要再发,订单结果已经确定");

        System.out.println("结束时间：" + DateFormat.getDateTimeInstance().format(new Date()));

        return "success";
    }

    //模拟发货通知的结果
    public String getNoticeResult() throws IOException {
        //模拟调用通知发货API接口,获取返回结果
        String[] strs = {"success", "-error-", "--wait--", "-nice--"};
        return RandomStr(strs);

    }

    //随机返回字符串数组中的字符串
    public static String RandomStr(String[] strs) {
        int random_index = (int) (Math.random() * strs.length);
        return strs[random_index];
    }


}

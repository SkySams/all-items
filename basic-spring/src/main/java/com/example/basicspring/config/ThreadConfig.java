package com.example.basicspring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 线程池
 * @author: zyh
 * @date: 2023/2/6
 */
@Configuration
@EnableAsync
@ComponentScan("com.example.basicspring.service")
public class ThreadConfig {

    /**
     当池子大小小于corePoolSize，就新建线程，并处理请求
     当池子大小等于corePoolSize，把请求放入workQueue(QueueCapacity)中，池子里的空闲线程就去workQueue中取任务并处理
     当workQueue放不下任务时，就新建线程入池，并处理请求，如果池子大小撑到了maximumPoolSize，就用RejectedExecutionHandler来做拒绝处理
     当池子的线程数大于corePoolSize时，多余的线程会等待keepAliveTime长时间，如果无请求可处理就自行销毁
     */

    @Bean("getExecutor")
    public Executor getExecutor(){
        ThreadPoolTaskExecutor executor = new MyThreadPoolTaskExecutor();
        //设置核心线程数
        executor.setCorePoolSize(10);
        //设置最大线程数
        executor.setMaxPoolSize(100);
        //线程池所使用的缓冲队列
        executor.setQueueCapacity(250);
        // 设置线程名称
        executor.setThreadNamePrefix("SKYTest-Async");
        //设置多余线程等待的时间，单位：秒
        //executor.setKeepAliveSeconds();
        // 初始化线程
        executor.initialize();

        return executor;
    }

}

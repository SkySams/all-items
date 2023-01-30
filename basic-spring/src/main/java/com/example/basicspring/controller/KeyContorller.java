package com.example.basicspring.controller;

import com.example.basicspring.entity.vo.Student;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Springboot 一个注解搞定返回参数key转换 【实用】
 * @author: zyh
 * @date: 2023/1/30
 */
@RestController
public class KeyContorller {

    @GetMapping("/queryById")
    public Student queryById(Integer id) throws InterruptedException {
        //  代码消耗时间
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Student student = new Student();
        student.setId(id);
        student.setName("JCccc");
        student.setAge(20);
        student.setMobile("136XXXXXXXX");
        Thread.sleep(5);
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
        return student;
    }
}
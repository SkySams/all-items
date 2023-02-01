package com.example.basicspring.controller;

/**
 * @author: zyh
 * @date: 2023/2/1
 */

import com.example.basicspring.annotation.RepeatDaMie;
import com.example.basicspring.entity.dto.PayOrderApply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


@RestController
public class TestController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @RepeatDaMie(second = 1000,describe = "尊敬的客户,您慢点")
    @PostMapping(value = "/doPost")
    @ResponseBody
    public void test(@RequestBody PayOrderApply payOrderApply) {
        log.info("Controller POST请求:"+payOrderApply.toString());
    }

    @RepeatDaMie(second = 1000,describe = "大哥,你冷静点")
    @GetMapping(value = "/doGet")
    @ResponseBody
    public void doGet( PayOrderApply payOrderApply) {
        log.info("Controller GET请求:"+payOrderApply.toString());
    }

}
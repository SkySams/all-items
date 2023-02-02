package com.example.basicspring.controller;

/**
 * @author: zyh
 * @date: 2023/2/1
 */

import com.example.basicspring.annotation.RepeatDaMie;
import com.example.basicspring.annotation.RequestLimit;
import com.example.basicspring.apireturn.ResultData;
import com.example.basicspring.entity.dto.PayOrderApply;
import com.example.basicspring.enums.CodeEnum;
import com.example.basicspring.enums.EducateStatusEnum;
import com.example.basicspring.message.LocaleMessage;
import com.example.basicspring.service.UserService;
import com.example.basicspring.util.EnumListUtil;
import com.example.basicspring.util.IpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@RestController
public class TestController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private LocaleMessage localeMessage;

    @RepeatDaMie(second = 1000,describe = "尊敬的客户,您慢点")
    @PostMapping(value = "/doPost")
    @ResponseBody
    public void test(@RequestBody PayOrderApply payOrderApply) {
        log.info("Controller POST请求:{}",payOrderApply.toString());
    }

    @RepeatDaMie(second = 1000,describe = "大哥,你冷静点")
    @GetMapping(value = "/doGet")
    @ResponseBody
    public void doGet(PayOrderApply payOrderApply) {
        log.info("Controller GET请求:"+payOrderApply.toString());
    }

    @GetMapping("/test")
    @RequestLimit(maxCount = 3,second = 60)
    public String test() {
        return "你好，如果对你有帮助，请点赞加关注。";
    }


    @PostMapping("doTest")
    public String doTest(@RequestParam("name") String name) throws InterruptedException {
        log.info("入参 name={}",name);
        testTrace();
        log.info("调用结束 name={}",name);
        return "Hello,"+name;
    }
    private void testTrace(){
        log.info("这是一行info日志");
        log.error("这是一行error日志");
        testTrace2();
    }
    private void testTrace2(){
        log.info("这也是一行info日志");

    }

    @GetMapping
    public String aysnc(){
        userService.testAsync();
        return "seccuss";
    }

    @GetMapping("i18n")
    public ResultData test(@RequestParam int testNum) {

        if (1==testNum){
            return ResultData.success(CodeEnum.SUCCESS);
        }
        if (2==testNum){
            return ResultData.success(CodeEnum.FAIL);
        }
        if (3==testNum){
            return ResultData.success("自定义的返回语");
        }

        return ResultData.success(CodeEnum.SUCCESS);
    }

    @GetMapping("enums")
    public List<Map<String, Object>> enums (){
        return EnumListUtil.enumToListMap(EducateStatusEnum.class);
    }


}
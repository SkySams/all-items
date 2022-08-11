package org.example.controller.async;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.example.service.async.AsyncService;
import org.example.util.enums.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author: zyh
 * @date: 2022/8/8
 */
@Slf4j
@Api(tags = "异步请求")
@RestController
public class AsyncController {

    @Resource
    private AsyncService asyncService;

    @ApiOperation("异步测试ONE")
    @GetMapping("async")
    public String test(){
        asyncService.wocao();
        return "success";
    }

    @ApiOperation("异步测试TWO")
    @GetMapping("async/two")
    public String asyncTwo() throws ExecutionException, InterruptedException, TimeoutException {
        Future<String> stringFuture =  asyncService.asyncMethod();
        String result = stringFuture.get(60, TimeUnit.SECONDS);
        return result;
    }

    @ApiOperation("泛型")
    @GetMapping("async/enum")
    public Person getPerson(){
        return Person.TYPE_ONE;
    }

}

package com.example.basicspring.config;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * @author: zyh
 * @date: 2023/1/30
 */
@ControllerAdvice  //全局Controller
public class MyInitBinderController {

    @InitBinder("specialCar")
    public void initCar(WebDataBinder webDataBinder) {
        webDataBinder.setFieldDefaultPrefix("param.");  // 接受参数的前缀
    }

    /**
     * 针对某个接口接口参数 起名字， 对的上的才做处理
     * @param webDataBinder
     */
    @InitBinder("user")
    public void initUser(WebDataBinder webDataBinder) {
        webDataBinder.setFieldDefaultPrefix("user."); // 接受参数的前缀
    }


}

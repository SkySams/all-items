package com.example.basicspring.controller;

import com.example.basicspring.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: zyh
 * @date: 2023/2/1
 */
@Controller
public class WebSocketController {

    @Autowired
    public SimpMessagingTemplate template;

    /**
     * 广播
     *
     * @param msg
     */
    @ResponseBody
    @RequestMapping("/pushToAll")
    public void subscribe( @RequestBody Message msg) {
        template.convertAndSend("/topic/all", msg.getContent());
    }


    /**
     * 点对点
     */
    @ResponseBody
    @MessageMapping("/alone")
    @RequestMapping("/pushToOne")
    public void queue(@RequestBody Message msg) {
        System.out.println("进入方法");
        /*使用convertAndSendToUser方法，第一个参数为用户id，此时js中的订阅地址为
        "/user/" + 用户Id + "/message",其中"/user"是固定的*/
        template.convertAndSendToUser(msg.getTo(), "/message", msg.getContent());
    }

}

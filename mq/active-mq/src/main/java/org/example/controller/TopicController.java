package org.example.controller;

import org.example.service.JmsMessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.jms.Topic;

/**
 * @author: zyh
 * @date: 2022/3/8
 */
@RestController
public class TopicController {

    @Autowired
    private Topic topic;

    @Resource
    private JmsMessagingService jmsMessagingService;

    @GetMapping("/topic/test")
    public String sendTopic( String str) {
        for(int i=0;i<=1000; i++){
            jmsMessagingService.sendMessage(this.topic, str+i);
        }
        return "success";
    }

}

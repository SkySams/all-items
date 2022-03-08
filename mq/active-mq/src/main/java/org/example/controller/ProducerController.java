package org.example.controller;

import org.example.service.JmsMessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.Topic;

/**
 * @author: zyh
 * @date: 2022/3/8
 */
@RestController
public class ProducerController {

    @Resource
    private JmsMessagingService jmsMessagingService;

    @Autowired
    private Queue queue;

    @GetMapping("/queue/test")
    public String sendQueue(String str) {
        jmsMessagingService.sendMessage(this.queue, str);
        return "success";
    }



}

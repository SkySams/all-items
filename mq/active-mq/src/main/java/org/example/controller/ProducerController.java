package org.example.controller;

import org.example.service.JmsMessagingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;

/**
 * @author: zyh
 * @date: 2022/3/8
 */
@RestController
public class ProducerController {

    private JmsMessagingService jmsMessagingService;

    private final Queue queue;

    public ProducerController(JmsMessagingService jmsMessagingService, Queue queue) {
        this.jmsMessagingService = jmsMessagingService;
        this.queue = queue;
    }

    @GetMapping("/queue/test")
    public String sendQueue(String str) {
        for (int i =0;i<10000; i++){
            jmsMessagingService.sendMessage(this.queue, str+i);
        }
        return "success";
    }



}

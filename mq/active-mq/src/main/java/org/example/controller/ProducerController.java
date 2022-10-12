package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.service.JmsMessagingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;

/**
 * @author: zyh
 * @date: 2022/3/8
 */
@Api(tags = "SEN")
@RestController
public class ProducerController {

    private JmsMessagingService jmsMessagingService;

    private final Queue queue;

    public ProducerController(JmsMessagingService jmsMessagingService, Queue queue) {
        this.jmsMessagingService = jmsMessagingService;
        this.queue = queue;
    }

    @ApiOperation("QUE")
    @GetMapping("/queue/test")
    public String sendQueue(@RequestParam("str") String str) {
        jmsMessagingService.sendMessage(this.queue, str,10000);
        return "success";
    }



}

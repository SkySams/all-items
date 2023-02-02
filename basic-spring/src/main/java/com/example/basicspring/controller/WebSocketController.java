package com.example.basicspring.controller;

import com.example.basicspring.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("/webSocket")
    public ModelAndView socket() {
        ModelAndView mav=new ModelAndView("/publicExample");
        return mav;
    }



}

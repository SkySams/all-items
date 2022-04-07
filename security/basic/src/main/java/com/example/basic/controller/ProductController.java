package com.example.basic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zyh
 * @date: 2022/4/7
 */
@RestController
@RequestMapping("product")
public class ProductController {

    @GetMapping("list")
    public String login(){
        return "list";
    }

}

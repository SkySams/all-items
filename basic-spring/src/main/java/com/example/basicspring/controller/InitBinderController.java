package com.example.basicspring.controller;

import com.example.basicspring.entity.MUser;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zyh
 * @date: 2023/1/30
 */
@RestController
public class InitBinderController {


    @PostMapping("user")
    public String doTest(@ModelAttribute("specialCar") MUser user){
        return "success";
    }

}

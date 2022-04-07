package com.example.basic.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zyh
 * @date: 2022/4/7
 */
@RestController
@RequestMapping("login")
public class LoginController {

    @GetMapping("home")
    public String login(){
        return "success";
    }

    @RequestMapping("/info")
    public String info(){
        String userDetails = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails) {
            userDetails = ((UserDetails)principal).getUsername();
        }else {
            userDetails = principal.toString();
        }
        return userDetails;
    }

}

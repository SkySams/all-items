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

    /**
     * SecurityContext是安全的上下文，所有的数据都是保存到SecurityContext中。
     * SecurityContextHolder用来获取SecurityContext中保存的数据的工具。通过使用静态方法获取SecurityContext的相对应的数据。
     *
     * Authentication
     * Authentication表示当前的认证情况，可以获取的对象有：
     * UserDetails：获取用户信息，是否锁定等额外信息。
     * Credentials：获取密码。
     * isAuthenticated：获取是否已经认证过。
     *
     * Principal：获取用户，如果没有认证，那么就是用户名，如果认证了，返回UserDetails。
     * @return
     */
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

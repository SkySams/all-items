package com.example.basic.configs;

import com.example.basic.entity.User;
import com.example.basic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * UserDetailsService可以通过loadUserByUsername获取UserDetails对象。该接口供spring security进行用户验证。
 * 通常使用自定义一个CustomUserDetailsService来实现UserDetailsService接口，通过自定义查询UserDetails。
 *
 * @author: zyh
 * @date: 2022/4/7
 */
@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        // 1. 查询用户
        User userFromDatabase = userRepository.findOneByLogin(login);
        if (userFromDatabase == null) {
            //log.warn("User: {} not found", login);
            throw new UsernameNotFoundException("User " + login + " was not found in db");
            //这里找不到必须抛异常
        }

        // 2. 设置角色
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userFromDatabase.getRole());
        grantedAuthorities.add(grantedAuthority);

        return new org.springframework.security.core.userdetails.User(login, userFromDatabase.getPassword(), grantedAuthorities);
    }

}
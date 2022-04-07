package com.example.basic.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * WebSecurityConfigurerAdapter 适配器
 *
 * @author: zyh
 * @date: 2022/4/7
 */
@Configuration
public class WebSecurityAdapter extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)// 设置自定义的userDetailsService
                .passwordEncoder(passwordEncoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().
                 sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/product/**").hasRole("USER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated() //
                .and()
                .formLogin()
                .and()
                .httpBasic()
                .and().logout().logoutUrl("/logout");
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("admin") // 添加用户admin
//                .password("{noop}admin")  // 不设置密码加密
//                .roles("ADMIN", "USER")// 添加角色为admin，user
//                .and()
//                .withUser("user") // 添加用户user
//                .password("{noop}user")
//                .roles("USER")
//                .and()
//                .withUser("tmp") // 添加用户tmp
//                .password("{noop}tmp")
//                .roles(); // 没有角色
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/login/**").hasRole("USER") //添加/product/** 下的所有请求只能由user角色才能访问
//                .antMatchers("/product/**").hasRole("ADMIN") //添加/admin/** 下的所有请求只能由admin角色才能访问
//                .anyRequest().authenticated() // 没有定义的请求，所有的角色都可以访问（tmp也可以）。
//                .and()
//                .formLogin().and()
//                .httpBasic();
//    }

    /**
     * PasswordEncoder
     * 密码加密器。通常是自定义指定。
     * BCryptPasswordEncoder：哈希算法加密
     * NoOpPasswordEncoder：不使用加密
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();// 使用不使用加密算法保持密码
//        return new BCryptPasswordEncoder();
    }
}

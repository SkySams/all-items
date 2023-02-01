package com.example.basicspring.config;


import com.example.basicspring.interceptor.ApiRepeatInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: zyh
 * @date: 2023/2/1
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ApiRepeatInterceptor()).addPathPatterns("/**");
    }
}
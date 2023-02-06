package com.example.basicspring.config;


import com.example.basicspring.interceptor.ApiRepeatInterceptor;
import com.example.basicspring.interceptor.CORSInterceptor;
import com.example.basicspring.interceptor.LogInterceptor;
import com.example.basicspring.interceptor.RequestLimitInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebConfigurerAdapter.java 添加拦截器
 *
 * @author: zyh
 * @date: 2023/2/1
 */
@Configuration
public class WebConfigurerAdapter implements WebMvcConfigurer {

    @Autowired
    private RequestLimitInterceptor requestLimitInterceptor;
    @Autowired
    private LogInterceptor logInterceptor;
    @Autowired
    private ApiRepeatInterceptor apiRepeatInterceptor;
    @Autowired
    private CORSInterceptor corsInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(corsInterceptor);
        registry.addInterceptor(logInterceptor).addPathPatterns("/**");
        registry.addInterceptor(apiRepeatInterceptor).addPathPatterns("/**");
        registry.addInterceptor(requestLimitInterceptor).addPathPatterns("/**")
                //再设置 放开哪些路径
                .excludePathPatterns("/static/**","/auth/login");
        //可以具体制定哪些需要拦截，哪些不拦截，其实也可以使用自定义注解更灵活完成
//                .addPathPatterns("/**")
//                .excludePathPatterns("/testxx.html");
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    }
}
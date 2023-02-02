package com.example.basicspring.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.Arrays;

/**
 * EnableWebSocketMessageBroker-注解开启STOMP协议来传输基于代理的消息，此时控制器支持使用@MessageMapping
 *
 * @author: zyh
 * @date: 2023/2/1
 */

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        //topic用来广播，user用来实现点对点
        config.enableSimpleBroker("/topic", "/user");
    }

    /**
     * 开放节点
     *
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //注册两个STOMP的endpoint，分别用于广播和点对点
        //广播
        // setAllowedOrigins 会出现跨域问题
        registry.addEndpoint("/publicServer").setAllowedOriginPatterns("*").withSockJS();

        //点对点
        registry.addEndpoint("/privateServer").setAllowedOriginPatterns("*").withSockJS();
    }

//    @Bean
//    public CorsFilter corsFilter() {
//        // 1.配置CORS信息
//        CorsConfiguration config = new CorsConfiguration();
//        // 哪些原始域需要放行
//        config.addAllowedOriginPattern("*"); //        config.addAllowedOrigin("*");
//        // 哪些原始域需要放行(请求方式)
//        config.addAllowedMethod("*");
//        // 放行哪些原始域(头部信息)
//        config.addAllowedHeader("*");
//        // 是否发送Cookie信息
//        config.setAllowCredentials(true);
//        // 暴露哪些头部信息（因为跨域访问默认，所以不能获取到全部的头部信息）
//        config.addExposedHeader(HttpHeaders.LOCATION);
//        config.setExposedHeaders(Arrays.asList("JSESSIONID", "SESSION", "token", HttpHeaders.LOCATION,
//                 HttpHeaders.ACCEPT, HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS,
//                 HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS,
//                HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, HttpHeaders.COOKIE, HttpHeaders.SET_COOKIE,
//                HttpHeaders.SET_COOKIE2));
//        // 2.添加映射路径实例
//        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
//        configSource.registerCorsConfiguration("/**", config);
//        // 3.返回新的CorsFilter.
//        return new CorsFilter(configSource);
//    }


}

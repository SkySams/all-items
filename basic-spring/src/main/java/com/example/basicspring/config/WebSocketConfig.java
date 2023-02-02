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


}

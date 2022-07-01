package org.example.configs;

import org.example.filter.CustomGlobalFilter;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: zyh
 * @date: 2022/6/14
 */
@Configuration
public class GatewayConfig {


    @Bean
    public GlobalFilter GlobalFilter(){
        return new CustomGlobalFilter();
    }

}

package org.example.futureitem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author: zyh
 * @date: 2022/3/8
 */
@Configuration
public class FutureConfigFactory {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}

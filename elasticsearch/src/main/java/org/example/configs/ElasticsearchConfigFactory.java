package org.example.configs;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

import java.time.Duration;

/**
 * @author: zyh
 * @date: 2022/3/28
 */
@Configuration
public class ElasticsearchConfigFactory {

    /**
     *
     * kibana 新增|更改 账号密码
     *kibana-keystore add elasticsearch.username
     *kibana-keystore add elasticsearch.password

     更改密码
     https://www.cnblogs.com/wwjj4811/p/14700279.html

     curl -H "Content-Type:application/json" -XPOST -u elastic 'http://127.0.0.1:9200/_xpack/security/user/elastic/_password'
     -d '{ "password" : "123456" }'
     */

    @Value("${spring.data.elasticsearch.client.reactive.username}")
    private String username;

    @Value("${spring.data.elasticsearch.client.reactive.password}")
    private String password;

    @Bean
    public RestHighLevelClient restHighLevelClient() {

        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("127.0.0.1:9200")
                .withConnectTimeout(Duration.ofSeconds(5))
                .withSocketTimeout(Duration.ofSeconds(3))
                .withBasicAuth(username, password)
                .build();
        return RestClients.create(clientConfiguration).rest();
    }
}

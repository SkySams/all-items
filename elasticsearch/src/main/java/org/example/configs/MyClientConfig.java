package org.example.configs;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

/**
 * 测试
 * @author: zyh
 * @date: 2022/12/12
 */
@Configuration
public class MyClientConfig  extends AbstractElasticsearchConfiguration {

    /**
     * Return the {@link RestHighLevelClient} instance used to connect to the cluster. <br />
     *
     * @return never {@literal null}.
     */
    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {

        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .build();

        return RestClients.create(clientConfiguration).rest();
    }
}






package org.example.configs;

import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.client.reactive.ReactiveElasticsearchClient;
import org.springframework.data.elasticsearch.client.reactive.ReactiveRestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.http.HttpHeaders;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author: zyh
 * @date: 2022/3/28
 */
@Configuration
public class ElasticsearchConfigFactory {

    /**
     *
     * kibana 新增|更改 账号密码
     *
     kibana-keystore create
     kibana-keystore add elasticsearch.username elastic
     kibana-keystore add elasticsearch.password elastic.123


     操作：
     lihuandeMacBook-Pro:bin lihuan$ ls
     kibana		kibana-keystore	kibana-plugin
     lihuandeMacBook-Pro:bin lihuan$ ./kibana-keystore list
     ERROR: Kibana keystore not found. Use 'create' command to create one.


     更改密码
     https://www.cnblogs.com/wwjj4811/p/14700279.html

     curl -H "Content-Type:application/json" -XPOST -u elastic 'http://127.0.0.1:9200/_xpack/security/user/elastic/_password'
     -d '{ "password" : "123456" }'

     ElasticsearchRestTemplate 是 spring-data-elasticsearch 项目中的一个类，和其他 spring 项目中的 template类似。
     在新版的 spring-data-elasticsearch 中，ElasticsearchRestTemplate 代替了原来的 ElasticsearchTemplate。
     原因是 ElasticsearchTemplate 基于 TransportClient，TransportClient 即将在 8.x 以后的版本中移除。所以，我们推荐使用 ElasticsearchRestTemplate。
     ElasticsearchRestTemplate 基 于 RestHighLevelClient 客户端的。需要自定义配置类，继承AbstractElasticsearchConfiguration，
     并实现 elasticsearchClient()抽象方法，创建 RestHighLevelClient 对象。
     */



    @Value("${spring.data.elasticsearch.client.reactive.username}")
    private String username;

    @Value("${spring.data.elasticsearch.client.reactive.password}")
    private String password;

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        return RestClients.create(clientConfiguration()).rest();
    }

    public ClientConfiguration clientConfiguration (){
      final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("127.0.0.1:9200")
                .withConnectTimeout(Duration.ofSeconds(5))
                .withSocketTimeout(Duration.ofSeconds(3))
                .withBasicAuth(username, password)
//                .withPathPrefix("ela") // 谨慎添加，创建索引都会在前缀上ela
                .build();
        return clientConfiguration;
    }


}

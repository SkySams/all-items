package org.example.controller.client;

import io.swagger.annotations.Api;
import org.elasticsearch.client.ElasticsearchClient;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.client.reactive.ReactiveElasticsearchClient;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zyh
 * @date: 2022/12/12
 */
@Api(tags = "CLIENT")
@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ElasticsearchOperations operations;




}

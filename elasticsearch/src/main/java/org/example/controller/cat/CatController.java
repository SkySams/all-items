package org.example.controller.cat;

import io.swagger.annotations.Api;
import org.elasticsearch.client.RestHighLevelClient;
import org.example.esdao.ElasticsearchDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zyh
 * @date: 2023/1/17
 */



@Api(tags = "CAT")
@RestController
@RequestMapping("/cat")
public class CatController {

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private ElasticsearchDBRepository elasticsearchDBRepository;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;


}

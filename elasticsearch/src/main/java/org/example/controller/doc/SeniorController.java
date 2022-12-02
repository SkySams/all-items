package org.example.controller.doc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author: zyh
 * @date: 2022/12/2
 */

@Api(tags = "SENIOR")
@RestController
@RequestMapping("/senior")
public class SeniorController {
    @Autowired
    private RestHighLevelClient client;


    @ApiOperation("高级搜索")
    @PostMapping("{index}")
    @ApiImplicitParam(value = "索引名称", name = "index")
    public SearchResponse senior(@PathVariable("index") String index, @RequestBody Map map) throws Exception{
        SearchRequest request = new SearchRequest();
        request.indices(index);
        // 构建查询的请求体
        SearchSourceBuilder builder = new SearchSourceBuilder();
        // 查询所有数据
        builder.query(QueryBuilders.matchAllQuery());
        request.source(builder);
        SearchResponse response = client.search(request,RequestOptions.DEFAULT);
        // 查询匹配
        SearchHits hits = response.getHits();
        return response;
    }

}

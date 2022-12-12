package org.example.controller.search;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.explain.ExplainResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: zyh
 * @date: 2022/12/9
 */
@Api(tags = "SEARCH-COUNT-API")
@RestController
@RequestMapping("/search/count")
public class CountController {

    @Autowired
    private RestHighLevelClient client;

    @ApiOperation(value = "SEARCH-COUNT-API")
    @PostMapping("/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index",dataTypeClass = String.class)
    public CountResponse count(@PathVariable String index, @RequestParam String id) throws Exception {
        CountRequest countRequest = new CountRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        countRequest.source(searchSourceBuilder);
        CountResponse countResponse = client.count(countRequest, RequestOptions.DEFAULT);
        return countResponse;
    }
}

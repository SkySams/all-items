package org.example.controller.search;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.search.MultiSearchRequest;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: zyh
 * @date: 2022/12/9
 */
@Api(tags = "SEARCH-MULTI-API")
@RestController
@RequestMapping("/search/multi/index")
public class SearchMultiController {

    @Autowired
    private RestHighLevelClient client;

    @ApiOperation(value = "SEARCH-MULTI-API（多值查询）")
    @PostMapping("/{index}/{size}")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "索引名称", name = "index"),
            @ApiImplicitParam(value = "大小", name = "size"),
            @ApiImplicitParam(value = "字段", name = "field"),
            @ApiImplicitParam(value = "值", name = "value"),
    })
    public MultiSearchResponse searchApi(@PathVariable String index, @PathVariable int size, @RequestParam String field, @RequestParam String value) throws Exception {
        MultiSearchRequest multiSearchRequest = new MultiSearchRequest();
        SearchRequest request = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery(field, value));
        request.source(searchSourceBuilder);
        multiSearchRequest.add(request);

        SearchRequest secondSearchRequest = new SearchRequest(index);
        searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("age", 425));
        secondSearchRequest.source(searchSourceBuilder);
        multiSearchRequest.add(secondSearchRequest);
        MultiSearchResponse response = client.msearch(multiSearchRequest, RequestOptions.DEFAULT);
        return response;
    }


}

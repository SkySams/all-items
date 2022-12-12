package org.example.controller.search;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.search.*;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: zyh
 * @date: 2022/12/9
 */
@Api(tags = "SEARCH-SCROLL-API")
@RestController
@RequestMapping("/search/scroll/index")
public class SearchScrollController {

    @Autowired
    private RestHighLevelClient client;

    @ApiOperation(value = "SEARCH-API")
    @PostMapping("/{index}/{size}")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "索引名称", name = "index",dataTypeClass = String.class),
            @ApiImplicitParam(value = "大小", name = "size",dataTypeClass = Integer.class),
            @ApiImplicitParam(value = "字段", name = "field",dataTypeClass = String.class),
            @ApiImplicitParam(value = "值", name = "value",dataTypeClass = String.class),
    })
    public SearchResponse searchApi(@PathVariable String index, @PathVariable int size,@RequestParam String field, @RequestParam String value) throws Exception {
        SearchRequest request = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery(field, value));
        searchSourceBuilder.size(size);
        request.source(searchSourceBuilder);
        request.scroll(TimeValue.timeValueMinutes(1L));
        SearchResponse searchResponse = client.search(request, RequestOptions.DEFAULT);
        String scrollId = searchResponse.getScrollId();
        SearchHits hits = searchResponse.getHits();
        System.out.println(scrollId);
        return searchResponse;
    }

    @ApiOperation(value = "SEARCH-FULL-EXAMPLE-API")
    @PostMapping("/full/example/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index",dataTypeClass = String.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "索引名称", name = "index",dataTypeClass = String.class),
            @ApiImplicitParam(value = "字段", name = "field",dataTypeClass = String.class),
            @ApiImplicitParam(value = "值", name = "value",dataTypeClass = String.class),
    })
    public SearchResponse searchFullExample(@PathVariable String index,@RequestParam String field, @RequestParam String value) throws Exception {
        final Scroll scroll = new Scroll(TimeValue.timeValueMinutes(1L));
        SearchRequest request = new SearchRequest(index);
        request.scroll(scroll);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery(field,value));
        request.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(request, RequestOptions.DEFAULT);
        String scrollId = searchResponse.getScrollId();
        System.out.println(scrollId);
        SearchHit[] searchHits = searchResponse.getHits().getHits();

        while (searchHits != null && searchHits.length > 0) {
            SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
            scrollRequest.scroll(scroll);
            searchResponse = client.scroll(scrollRequest, RequestOptions.DEFAULT);
            scrollId = searchResponse.getScrollId();
            searchHits = searchResponse.getHits().getHits();
        }

        ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
        clearScrollRequest.addScrollId(scrollId);
        ClearScrollResponse clearScrollResponse = client.clearScroll(clearScrollRequest, RequestOptions.DEFAULT);
        boolean succeeded = clearScrollResponse.isSucceeded();
        System.out.println(succeeded);

        return searchResponse;
    }

}

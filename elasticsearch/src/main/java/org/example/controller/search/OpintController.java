package org.example.controller.search;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.search.builder.PointInTimeBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: zyh
 * @date: 2022/12/9
 */
@Api(tags = "SEARCH-OPINT-API")
@RestController
@RequestMapping("/search/opint")
public class OpintController {

    @Autowired
    private RestHighLevelClient client;

    @ApiOperation(value = "SEARCH-OPINT-API",notes = "fail")
    @PostMapping("/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index",dataTypeClass = String.class)
    public CountResponse count(@PathVariable String index, @RequestParam String pitId) throws Exception {
        SearchRequest searchRequest = new SearchRequest();
        final PointInTimeBuilder pointInTimeBuilder = new PointInTimeBuilder(pitId);
        searchRequest.source(new SearchSourceBuilder().pointInTimeBuilder(pointInTimeBuilder));
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        return null;
    }

}

package org.example.controller.search;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.asyncsearch.AsyncSearchResponse;
import org.elasticsearch.client.asyncsearch.DeleteAsyncSearchRequest;
import org.elasticsearch.client.asyncsearch.GetAsyncSearchRequest;
import org.elasticsearch.client.asyncsearch.SubmitAsyncSearchRequest;
import org.elasticsearch.client.core.AcknowledgedResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: zyh
 * @date: 2022/12/9
 */
@Api(tags = "SEARCH-ASYNC-API")
@RestController
@RequestMapping("/search/async")
public class SearchAsyncController {

    @Autowired
    private RestHighLevelClient client;

    @ApiOperation(value = "SEARCH-ASYNC-API")
    @GetMapping
    public AsyncSearchResponse async() throws Exception {
        SearchSourceBuilder searchSource = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        String[] indices = new String[]{"employee", "person"};
        SubmitAsyncSearchRequest request = new SubmitAsyncSearchRequest(searchSource, indices);
        request.setWaitForCompletionTimeout(TimeValue.timeValueSeconds(30));
        request.setKeepAlive(TimeValue.timeValueMinutes(15));
        request.setKeepOnCompletion(false);
        AsyncSearchResponse response = client.asyncSearch().submit(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "GET-ASYNC-API")
    @GetMapping("get")
    public AsyncSearchResponse getAsync(@RequestParam String id) throws Exception {
        GetAsyncSearchRequest request = new GetAsyncSearchRequest(id);
        request.setWaitForCompletion(TimeValue.timeValueSeconds(30));
        request.setKeepAlive(TimeValue.timeValueMinutes(15));
        AsyncSearchResponse response = client.asyncSearch().get(request, RequestOptions.DEFAULT);
        response.getSearchResponse();
        response.getId();
        response.isPartial();
        response.isRunning();
        response.getStartTime();
        response.getExpirationTime();
        response.getFailure();
        return response;
    }

    @ApiOperation(value = "DELETE")
    @DeleteMapping("delete")
    public AcknowledgedResponse deleted(@RequestParam String id) throws Exception {
        DeleteAsyncSearchRequest request = new DeleteAsyncSearchRequest(id);
        AcknowledgedResponse response = client.asyncSearch().delete(new DeleteAsyncSearchRequest(id), RequestOptions.DEFAULT);
        return response;
    }

}

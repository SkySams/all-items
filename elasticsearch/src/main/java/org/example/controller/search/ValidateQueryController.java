package org.example.controller.search;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.admin.indices.validate.query.QueryExplanation;
import org.elasticsearch.action.admin.indices.validate.query.ValidateQueryRequest;
import org.elasticsearch.action.admin.indices.validate.query.ValidateQueryResponse;
import org.elasticsearch.action.support.DefaultShardOperationFailedException;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zyh
 * @date: 2022/12/9
 */
@Api(tags = "验证API")
@RestController
@RequestMapping("/search/validate")
public class ValidateQueryController {

    @Autowired
    private RestHighLevelClient client;

    @ApiOperation("验证QUERY")
    @PutMapping("/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index")
    public ValidateQueryResponse indexSettings(@PathVariable String index) throws Exception {
        ValidateQueryRequest request = new ValidateQueryRequest(index);
        QueryBuilder builder = QueryBuilders
                .boolQuery()
                .must(QueryBuilders.queryStringQuery("*:*"));
//                .filter(QueryBuilders.termQuery("user", "kimchy"));
        request.query(builder);
        ValidateQueryResponse response = client.indices().validateQuery(request, RequestOptions.DEFAULT);

        boolean isValid = response.isValid();
        int totalShards = response.getTotalShards();
        int successfulShards = response.getSuccessfulShards();
        int failedShards = response.getFailedShards();
        if (failedShards > 0) {
            for(DefaultShardOperationFailedException failure: response.getShardFailures()) {
                String failedIndex = failure.index();
                int shardId = failure.shardId();
                String reason = failure.reason();
            }
        }
        for(QueryExplanation explanation: response.getQueryExplanation()) {
            String explanationIndex = explanation.getIndex();
            int shardId = explanation.getShard();
            String explanationString = explanation.getExplanation();
        }

        return response;
    }

}

package org.example.controller.search;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.lucene.search.Explanation;
import org.elasticsearch.action.explain.ExplainRequest;
import org.elasticsearch.action.explain.ExplainResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.get.GetResult;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.rankeval.RankEvalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: zyh
 * @date: 2022/12/9
 */
@Api(tags = "SEARCH-EXPLAIN-API")
@RestController
@RequestMapping("/search/explain")
public class ExplainController {

    @Autowired
    private RestHighLevelClient client;

    @ApiOperation(value = "SEARCH-EXPLAIN-API")
    @PostMapping("/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index",dataTypeClass = String.class)
    public ExplainResponse explain(@PathVariable String index, @RequestParam String id) throws Exception {
        ExplainRequest request = new ExplainRequest(index,id);
        request.query(QueryBuilders.matchAllQuery());
        // 设置路由
        // request.routing("routing");
        ExplainResponse response = client.explain(request, RequestOptions.DEFAULT);
        String exindex = response.getIndex();
        String exid = response.getId();
        boolean exists = response.isExists();
        boolean match = response.isMatch();
        boolean hasExplanation = response.hasExplanation();
        Explanation explanation = response.getExplanation();
        GetResult getResult = response.getGetResult();
        return response;
    }


}

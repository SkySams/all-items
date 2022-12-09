package org.example.controller.search;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.rankeval.*;
import org.elasticsearch.script.mustache.SearchTemplateResponse;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: zyh
 * @date: 2022/12/9
 */
@Api(tags = "SEARCH-RANKING-API")
@RestController
@RequestMapping("/search/ranking/index")
public class RankingController {

    @Autowired
    private RestHighLevelClient client;

    @ApiOperation(value = "SEARCH-TEMPLATE-API")
    @PostMapping("/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index")
    public RankEvalResponse searchRanking(@PathVariable String index, @RequestParam String field, @RequestParam String value) throws Exception {
        EvaluationMetric metric = new PrecisionAtK();
        List<RatedDocument> ratedDocs = new ArrayList<>();
        ratedDocs.add(new RatedDocument(index, "371", 1));
        SearchSourceBuilder searchQuery = new SearchSourceBuilder();
        searchQuery.query(QueryBuilders.matchQuery(field, value));
        RatedRequest ratedRequest = new RatedRequest("kimchy_query", ratedDocs, searchQuery);
        List<RatedRequest> ratedRequests = Arrays.asList(ratedRequest);
        RankEvalSpec specification =
                new RankEvalSpec(ratedRequests, metric);
        RankEvalRequest request = new RankEvalRequest(specification, new String[] { index });
        RankEvalResponse response = client.rankEval(request, RequestOptions.DEFAULT);
        return response;
    }


}

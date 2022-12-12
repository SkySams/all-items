package org.example.controller.search;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.ShardSearchFailure;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.SuggestionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author: zyh
 * @date: 2022/12/8
 */
@Api(tags = "SEARCH-API")
@RestController
@RequestMapping("/search/index")
public class SearchController {

    @Autowired
    private RestHighLevelClient client;

    @ApiOperation(value = "SEARCH-API")
    @PostMapping("/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index",dataTypeClass = String.class)
    public SearchResponse searchApi(@PathVariable String index) throws Exception {
        SearchRequest request = new SearchRequest(index);
        request.indicesOptions(IndicesOptions.lenientExpandOpen());
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        request.source(searchSourceBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        this.getSearchResponse(response);
        return response;
    }

    @ApiOperation(value = "SEARCH-PAGE-API")
    @PostMapping("/page/{index}/{from}/{size}/{duration}")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "索引名称", name = "index",dataTypeClass = String.class),
            @ApiImplicitParam(value = "当前页", name = "from",dataTypeClass = Integer.class),
            @ApiImplicitParam(value = "大小", name = "size",dataTypeClass = Integer.class),
            @ApiImplicitParam(value = "期间", name = "duration",dataTypeClass = Long.class),
            @ApiImplicitParam(value = "key", name = "key",dataTypeClass = String.class),
            @ApiImplicitParam(value = "value", name = "value",dataTypeClass = String.class),
    })
    public SearchResponse searchPage(@PathVariable String index, @PathVariable int from, @PathVariable int size,
                                     @PathVariable long duration,
                                     @RequestParam String key, @RequestParam String value) throws Exception {
        SearchRequest request = new SearchRequest(index);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.termQuery(key, value));
        sourceBuilder.from(from);
        sourceBuilder.size(size);
        // 排序
        sourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));
        sourceBuilder.sort(new FieldSortBuilder("age").order(SortOrder.ASC));

        sourceBuilder.timeout(new TimeValue(duration, TimeUnit.SECONDS));
        request.source(sourceBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "SEARCH-HIGH-API")
    @PostMapping("/page/highlight/{index}")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "索引名称", name = "index",dataTypeClass = String.class),
            @ApiImplicitParam(value = "key", name = "key",dataTypeClass = String.class)
    })
    public SearchResponse searchHighlight(@PathVariable String index, @RequestParam String key) throws Exception {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        SearchRequest request = new SearchRequest(index);
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        HighlightBuilder.Field highlightTitle = new HighlightBuilder.Field(key);
        highlightTitle.highlighterType("unified");
        highlightBuilder.field(highlightTitle);
//        HighlightBuilder.Field highlightUser = new HighlightBuilder.Field("user");
//        highlightBuilder.field(highlightUser);
        builder.highlighter(highlightBuilder);
        request.source(builder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "SEARCH-AGGREGATION-API")
    @PostMapping("/aggregations/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index",dataTypeClass = String.class)
    public SearchResponse searchAggregations(@PathVariable String index) throws Exception {
        SearchRequest request = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        TermsAggregationBuilder aggregation = AggregationBuilders.terms("by_company").field("company.keyword");
        aggregation.subAggregation(AggregationBuilders.avg("average_age").field("age"));
        searchSourceBuilder.aggregation(aggregation);
        //  _source 是否返回数据
        searchSourceBuilder.fetchSource(false);
        request.source(searchSourceBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "SEARCH-BUILDING-API")
    @PostMapping("/builder/{index}")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "索引名称", name = "index",dataTypeClass = String.class),
            @ApiImplicitParam(value = "字段", name = "field",dataTypeClass = String.class),
            @ApiImplicitParam(value = "值", name = "value",dataTypeClass = String.class),
    })
    public SearchResponse searchBuildingQueries(@PathVariable String index,@RequestParam String field,@RequestParam String value) throws Exception {
        SearchRequest request = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder(field,value);
        // 对匹配查询启用模糊匹配
        matchQueryBuilder.fuzziness(Fuzziness.AUTO);
        // 设置匹配查询的前缀长度选项
        matchQueryBuilder.prefixLength(3);
        // 设置最大扩展选项以控制查询的模糊过程
        matchQueryBuilder.maxExpansions(10);

        searchSourceBuilder.query(matchQueryBuilder);
        request.source(searchSourceBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "SEARCH-SUGGESIONS-API")
    @PostMapping("/suggestions/{index}")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "索引名称", name = "index",dataTypeClass = String.class),
            @ApiImplicitParam(value = "字段", name = "field",dataTypeClass = String.class),
            @ApiImplicitParam(value = "值", name = "value",dataTypeClass = String.class),
    })
    public SearchResponse searchSuggestions(@PathVariable String index,@RequestParam String field,@RequestParam String value) throws Exception {
        SearchRequest request = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        SuggestionBuilder  termSuggestionBuilder = SuggestBuilders.termSuggestion(field).text(value);
        SuggestBuilder suggestBuilder = new SuggestBuilder();
        suggestBuilder.addSuggestion("suggest_user",termSuggestionBuilder);
        searchSourceBuilder.suggest(suggestBuilder);
        searchSourceBuilder.profile(true);
        request.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(request, RequestOptions.DEFAULT);
        return searchResponse;
    }

    private void getSearchResponse(SearchResponse searchResponse){
        RestStatus status = searchResponse.status();
        TimeValue took = searchResponse.getTook();
        Boolean terminatedEarly = searchResponse.isTerminatedEarly();
        boolean timedOut = searchResponse.isTimedOut();
        System.out.println(status);
        System.out.println(took);
        System.out.println(terminatedEarly);
        System.out.println(timedOut);

        int totalShards = searchResponse.getTotalShards();
        int successfulShards = searchResponse.getSuccessfulShards();
        int failedShards = searchResponse.getFailedShards();

        System.out.println(totalShards);
        System.out.println(successfulShards);
        System.out.println(failedShards);

        for (ShardSearchFailure failure : searchResponse.getShardFailures()) {
            // failures should be handled here
            System.out.println(failure.index());
        }

        SearchHits hits = searchResponse.getHits();

        TotalHits totalHits = hits.getTotalHits();

        long numHits = totalHits.value;
        System.out.println(numHits);
        TotalHits.Relation relation = totalHits.relation;
        float maxScore = hits.getMaxScore();

        SearchHit[] searchHits = hits.getHits();
        for (SearchHit hit : searchHits) {
            String index = hit.getIndex();
            String id = hit.getId();
            float score = hit.getScore();

            System.out.println(index);
            System.out.println(id);
            System.out.println(score);

            String sourceAsString = hit.getSourceAsString();
            System.out.println(sourceAsString);

            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            String documentTitle = (String) sourceAsMap.get("lastName");
            System.out.println(documentTitle);

        }

//        Aggregations aggregations = searchResponse.getAggregations();
//        Terms byCompanyAggregation = aggregations.get("by_company");
//        Terms.Bucket elasticBucket = byCompanyAggregation.getBucketByKey("Elastic");
//        Avg averageAge = elasticBucket.getAggregations().get("average_age");
//        double avg = averageAge.getValue();
//        System.out.println(avg);

    }

}

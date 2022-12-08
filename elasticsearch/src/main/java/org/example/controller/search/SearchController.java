package org.example.controller.search;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @ApiImplicitParam(value = "索引名称", name = "index")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "索引", name = "index")
    })
    public SearchResponse searchApi(@PathVariable String index) throws Exception {
        SearchRequest request = new SearchRequest(index);
        request.indicesOptions(IndicesOptions.lenientExpandOpen());
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        request.source(searchSourceBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "SEARCH-PAGE-API")
    @PostMapping("/page/{index}/{from}/{size}/{duration}")
    @ApiImplicitParam(value = "索引名称", name = "index")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "索引", name = "index"),
            @ApiImplicitParam(value = "当前页", name = "from"),
            @ApiImplicitParam(value = "大小", name = "size"),
            @ApiImplicitParam(value = "期间", name = "duration"),
            @ApiImplicitParam(value = "key", name = "key"),
            @ApiImplicitParam(value = "value", name = "value"),
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
    @ApiImplicitParam(value = "索引名称", name = "index")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "索引", name = "index"),
            @ApiImplicitParam(value = "key", name = "key")
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
    @ApiImplicitParam(value = "索引名称", name = "index")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "索引", name = "index")
    })
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
    @ApiImplicitParam(value = "索引名称", name = "index")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "索引", name = "index"),
            @ApiImplicitParam(value = "字段", name = "field"),
            @ApiImplicitParam(value = "值", name = "value"),
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
    @ApiImplicitParam(value = "索引名称", name = "index")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "索引", name = "index"),
            @ApiImplicitParam(value = "字段", name = "field"),
            @ApiImplicitParam(value = "值", name = "value"),
    })
    public SearchResponse searchSuggestions(@PathVariable String index,@RequestParam String field,@RequestParam String value) throws Exception {

        return null;
    }

}

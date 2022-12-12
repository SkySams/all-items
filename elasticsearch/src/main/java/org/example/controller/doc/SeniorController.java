package org.example.controller.doc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.profile.ProfileShardResult;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author: zyh
 * @date: 2022/12/2
 */

@Api(tags = "SENIOR")
@RestController
@RequestMapping("/senior")
public class SeniorController {
    @Autowired
    private RestHighLevelClient client;


    @ApiOperation("高级搜索")
    @PostMapping("{index}")
    @ApiImplicitParam(value = "索引名称", name = "index",dataTypeClass = String.class)
    public SearchResponse senior(@PathVariable("index") String index, @RequestBody Map map) throws Exception{
        SearchRequest request = new SearchRequest();
        request.indices(index);
        // 构建查询的请求体
        SearchSourceBuilder builder = new SearchSourceBuilder();
        // 查询所有数据
        builder.query(QueryBuilders.matchAllQuery());
        request.source(builder);
        SearchResponse response = client.search(request,RequestOptions.DEFAULT);
        // 查询匹配
        SearchHits hits = response.getHits();
        return response;
    }

    @ApiOperation(value = "term查询",notes = "term 查询，查询条件为关键字")
    @PostMapping("term/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index",dataTypeClass = String.class)
    public SearchResponse searchTerm(@PathVariable("index") String index, @RequestBody Map map) throws Exception{
        //创建搜索请求对象
        SearchRequest request = new SearchRequest(index);
        // 创建查询请求体
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.termQuery("age", map.get("age")));
        request.source(builder);
        SearchResponse response = client.search(request,RequestOptions.DEFAULT);
        return response;
    }
    @ApiOperation(value = "分页",notes = "分页查询")
    @PostMapping("page/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index",dataTypeClass = String.class)
    public SearchResponse searchPage(@PathVariable("index") String index, @RequestBody Map map, @RequestParam Integer current,@RequestParam Integer size) throws Exception{
        SearchRequest request = new SearchRequest(index);
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchAllQuery());
        // 分页查询
        // 当前页其实索引(第一条数据的顺序号)，from
        builder.from(current);
        builder.size(size);
        request.source(builder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "数据排序",notes = "数据排序")
    @PostMapping("page/orderby/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index",dataTypeClass = String.class)
    public SearchResponse searchPageOrderBy(@PathVariable("index") String index, @RequestBody Map map) throws Exception{
        SearchRequest request = new SearchRequest(index);
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchAllQuery());
        // 排序
        builder.sort("age", SortOrder.ASC);
        request.source(builder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "过滤字段",notes = "过滤字段")
    @PostMapping("page/filter/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index",dataTypeClass = String.class)
    public SearchResponse searchFilter(@PathVariable("index") String index, @RequestBody Map map) throws Exception{
        SearchRequest request = new SearchRequest(index);
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchAllQuery());

        // 过滤字段
        String [] excludes = {};
        String [] includes = {"name","age"};

        builder.fetchSource(excludes, includes);
        request.source(builder);
        SearchResponse response = client.search(request,RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "Bool查询",notes = "Bool查询")
    @PostMapping("page/bool/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index",dataTypeClass = String.class)
    public SearchResponse searchBool(@PathVariable("index") String index, @RequestBody Map map) throws Exception{
        SearchRequest request = new SearchRequest(index);
        // 构建查询的请求体
        SearchSourceBuilder builder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        // 必须包含
        boolQueryBuilder.must(QueryBuilders.matchQuery("age","1"));
        // 一定不包含
        boolQueryBuilder.mustNot(QueryBuilders.matchQuery("name","sky"));
        // 可能包含
        boolQueryBuilder.should(QueryBuilders.matchQuery("sex","男"));

        builder.query(boolQueryBuilder);
        request.source(builder);

        SearchResponse response = client.search(request,RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "范围查询",notes = "范围查询")
    @PostMapping("page/range/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index",dataTypeClass = String.class)
    public SearchResponse searchRangeQuery(@PathVariable("index") String index, @RequestBody Map map) throws Exception{
        SearchRequest request = new SearchRequest(index);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        RangeQueryBuilder rangeQuery = new RangeQueryBuilder("age");

        // 大于等于
        rangeQuery.gte(map.get("age"));
        // 小于等于
        rangeQuery.lte("40");
        searchSourceBuilder.query(rangeQuery);
        request.source(searchSourceBuilder);
        SearchResponse response = client.search(request,RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "模糊查询",notes = "模糊查询")
    @PostMapping("page/fuzzy/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index",dataTypeClass = String.class)
    public SearchResponse searchFuzzyQuery(@PathVariable("index") String index, @RequestBody Map map) throws Exception{
        SearchRequest request = new SearchRequest(index);
        // 构建查询的请求体
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.fuzzyQuery("name","sky").fuzziness(Fuzziness.ONE));
        request.source(sourceBuilder);
        SearchResponse response = client.search(request,RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "高亮查询",notes = "高亮查询")
    @PostMapping("page/highlight/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index",dataTypeClass = String.class)
    public SearchResponse searchHighlightBuilder (@PathVariable("index") String index, @RequestBody Map map) throws Exception{
        SearchRequest request = new SearchRequest().indices(index);
        //.创建查询请求体构建器
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //构建查询方式：高亮查询
        TermsQueryBuilder termsQueryBuilder = QueryBuilders.termsQuery("lastName",map.get("name"));
        //设置查询方式
        sourceBuilder.query(termsQueryBuilder);

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        //设置标签前缀
        highlightBuilder.preTags("<font color='red'>");
        //设置标签后缀
        highlightBuilder.postTags("</font>");
        //设置高亮字段
        highlightBuilder.field("name");
        //设置高亮构建对象
        sourceBuilder.highlighter(highlightBuilder);
        //设置请求体
        request.source(sourceBuilder);

        //3.客户端发送请求，获取响应对象
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "聚合查询",notes = "聚合查询")
    @PostMapping("page/polymerization/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index",dataTypeClass = String.class)
    public SearchResponse searchPolymerizationQuery (@PathVariable("index") String index, @RequestBody Map map) throws Exception{
        SearchRequest request = new SearchRequest(index);
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.aggregation(AggregationBuilders.max("maxAge").field("age"));

        request.source(builder);
        SearchResponse response = client.search(request,RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "组合查询",notes = "组合查询")
    @PostMapping("page/greoup/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index",dataTypeClass = String.class)
    public SearchResponse searchGroup (@PathVariable("index") String index, @RequestBody Map map) throws Exception{
        SearchRequest request = new SearchRequest(index);
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.aggregation(AggregationBuilders.terms("age_groupby").field("age"));

        request.source(builder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        return response;
    }





}

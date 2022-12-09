package org.example.controller.index;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.AnalyzeRequest;
import org.elasticsearch.client.indices.AnalyzeResponse;
import org.elasticsearch.client.indices.ReloadAnalyzersRequest;
import org.elasticsearch.client.indices.ReloadAnalyzersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: zyh
 * @date: 2022/12/9
 */
@Api(tags = "ANALYZE")
@RestController
@RequestMapping("/analyze/index")
public class IndexAnalyzeController {

    @Autowired
    private RestHighLevelClient client;


    @ApiOperation("创建索引-分析器")
    @PutMapping("")
    public void createAnalyze() throws Exception {
        Map<String, Object> stopFilter = new HashMap<>();
        stopFilter.put("type", "stop");
        stopFilter.put("stopwords", new String[]{ "to" });
        AnalyzeRequest request = AnalyzeRequest.buildCustomAnalyzer("standard")
                .addCharFilter("html_strip")
                .addTokenFilter("lowercase")
                .addTokenFilter(stopFilter)
                .build("<b>Some text to analyze</b>");
        AnalyzeResponse response = client.indices().analyze(request, RequestOptions.DEFAULT);
    }

    @ApiOperation("重新加载分析器请求")
    @PutMapping("/reload/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index")
    public ReloadAnalyzersResponse indexSettings(@PathVariable String index) throws Exception {
        ReloadAnalyzersRequest request = new ReloadAnalyzersRequest(index);
        ReloadAnalyzersResponse reloadResponse = client.indices().reloadAnalyzers(request, RequestOptions.DEFAULT);
        return reloadResponse;
    }

}

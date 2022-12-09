package org.example.controller.search;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.script.mustache.MultiSearchTemplateRequest;
import org.elasticsearch.script.mustache.MultiSearchTemplateResponse;
import org.elasticsearch.script.mustache.SearchTemplateRequest;
import org.elasticsearch.script.mustache.SearchTemplateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: zyh
 * @date: 2022/12/9
 */
@Api(tags = "SEARCH-TEMPLATE-API")
@RestController
@RequestMapping("/search/template/index")
public class SearchTemplateController {

    @Autowired
    private RestHighLevelClient client;

    @ApiOperation(value = "SEARCH-TEMPLATE-API")
    @PostMapping("/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index")
    public SearchTemplateResponse searchTemplateApi(@PathVariable String index, @RequestParam String field, @RequestParam String value) throws Exception {
        SearchTemplateRequest request = new SearchTemplateRequest();
        request.setRequest(new SearchRequest(index));
        request.setScriptType(ScriptType.INLINE);
        request.setScript(
                "{" +
                        "  \"query\": { \"match\" : { \"{{field}}\" : \"{{value}}\" } }," +
                        "  \"size\" : \"{{size}}\"" +
                        "}");
        Map<String, Object> scriptParams = new HashMap<>();
        scriptParams.put("field", field);
        scriptParams.put("value", value);
        scriptParams.put("size", 5);
        request.setScriptParams(scriptParams);
        SearchTemplateResponse response = client.searchTemplate(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "SEARCH-TEMPLATE-MULTI-API")
    @PostMapping("/multi/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "索引", name = "index")
    })
    public MultiSearchTemplateResponse searchTemplateMultiApi(@PathVariable String index) throws Exception {
        String [] searchTerms = {"constantin", "cheyanne", "jonathon"};
        MultiSearchTemplateRequest multiRequest = new MultiSearchTemplateRequest();
        for (String search : searchTerms){
            SearchTemplateRequest request = new SearchTemplateRequest();
            request.setRequest(new SearchRequest(index));
            request.setScriptType(ScriptType.INLINE);
            request.setScript(
                    "{" +
                            "  \"query\": { \"match\" : { \"{{field}}\" : \"{{value}}\" } }," +
                            "  \"size\" : \"{{size}}\"" +
                            "}");

            Map<String, Object> scriptParams = new HashMap<>();
            scriptParams.put("field", "firstName");
            scriptParams.put("value", search);
            scriptParams.put("size", 5);
            request.setScriptParams(scriptParams);
            multiRequest.add(request);
        }
        MultiSearchTemplateResponse multiResponse = client.msearchTemplate(multiRequest, RequestOptions.DEFAULT);
        return multiResponse;
    }


}

package org.example.controller.doc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

/**
 * @author: zyh
 * @date: 2022/12/6
 */
@Api(tags = "GET_API")
@RestController
@RequestMapping("/index/api")
public class GetApiController {

    @Autowired
    private RestHighLevelClient client;

    @ApiOperation(value = "类型1")
    @PutMapping("/{index}/{id}")
    @ApiImplicitParam(value = "索引名称", name = "index")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "索引", name = "index"),
            @ApiImplicitParam(value = "文本id", name = "id")
    })
    public GetResponse getApiType(@PathVariable String index, @PathVariable("id") String id) throws Exception {
        GetRequest request = new GetRequest(index, id);
        request.fetchSourceContext(FetchSourceContext.DO_NOT_FETCH_SOURCE);
        String[] includes = new String[]{"message", "*Date"};
        String[] excludes = Strings.EMPTY_ARRAY;
        FetchSourceContext fetchSourceContext = new FetchSourceContext(true, includes, excludes);
        request.fetchSourceContext(fetchSourceContext);
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "类型2")
    @PutMapping("/type1/{index}/{id}")
    @ApiImplicitParam(value = "索引名称", name = "index")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "索引", name = "index"),
            @ApiImplicitParam(value = "文本id", name = "id")
    })
    public GetResponse getApiType1(@PathVariable String index, @PathVariable("id") String id) throws Exception {
        GetRequest request = new GetRequest(index, id);
        request.fetchSourceContext(FetchSourceContext.DO_NOT_FETCH_SOURCE);
        String[] includes = Strings.EMPTY_ARRAY;
        String[] excludes = new String[]{"message"};
        FetchSourceContext fetchSourceContext = new FetchSourceContext(true, includes, excludes);
        request.fetchSourceContext(fetchSourceContext);
        request.storedFields("message");
        GetResponse getResponse = client.get(request, RequestOptions.DEFAULT);

        String responseIndex = getResponse.getIndex();
        String responseId = getResponse.getId();
        if (getResponse.isExists()) {
            long version = getResponse.getVersion();
            String sourceAsString = getResponse.getSourceAsString();
            Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();
            byte[] sourceAsBytes = getResponse.getSourceAsBytes();
        } else {

        }
        return getResponse;
    }

    @ApiOperation(value = "类型3")
    @PutMapping("/type3/{index}/{id}")
    @ApiImplicitParam(value = "索引名称", name = "index")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "索引", name = "index"),
            @ApiImplicitParam(value = "文本id", name = "id")
    })
    public GetResponse getApiType3(@PathVariable String index, @PathVariable("id") String id) throws IOException {
        GetRequest request = new GetRequest(index,id);
        GetResponse getResponse = null;
        try {
            getResponse = client.get(request, RequestOptions.DEFAULT);
        } catch (ElasticsearchException e) {
            if (e.status() == RestStatus.NOT_FOUND) {

            }
        }
        return getResponse;
    }

    @ApiOperation(value = "类型4")
    @PutMapping("/type4/{index}/{id}/{version}")
    @ApiImplicitParam(value = "索引名称", name = "index")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "索引", name = "index"),
            @ApiImplicitParam(value = "文本id", name = "id"),
            @ApiImplicitParam(value = "版本", name = "version")
    })
    public GetResponse getApiType4(@PathVariable String index, @PathVariable("id") String id, @PathVariable("version") Long version) throws IOException {
        GetResponse getResponse = null;
        try {
            GetRequest request = new GetRequest(index,id).version(version);
            getResponse = client.get(request, RequestOptions.DEFAULT);
        } catch (ElasticsearchException e) {
            if (e.status() == RestStatus.CONFLICT) {

            }
        }
        return getResponse;
    }

}

package org.example.controller.doc.source;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.GetSourceRequest;
import org.elasticsearch.client.core.GetSourceResponse;
import org.elasticsearch.common.Strings;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author: zyh
 * @date: 2022/12/6
 */
@Api(tags = "GET_SOURCE")
@RestController
@RequestMapping("/source")
public class GetSourceController {

    @Autowired
    private RestHighLevelClient client;


    @ApiOperation(value = "source_type1", notes = "")
    @PutMapping("/{index}/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "索引名称", name = "index",dataTypeClass = String.class),
            @ApiImplicitParam(value = "文本id", name = "id",dataTypeClass = String.class)
    })
    public Map sourceType1(@PathVariable String index, @PathVariable("id") String id) throws Exception {
        GetSourceRequest request = new GetSourceRequest(index, id);
        // 包含
        String[] includes = Strings.EMPTY_ARRAY;
        // 排除
        String[] excludes = new String[]{"postDate"};
        request.fetchSourceContext(
                new FetchSourceContext(true, includes, excludes));
        GetSourceResponse response = client.getSource(request, RequestOptions.DEFAULT);
        Map map = response.getSource();
        return map;
    }

}

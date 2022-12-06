package org.example.controller.doc.source;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zyh
 * @date: 2022/12/6
 */
@Api(tags = "EXISTS")
@RestController
@RequestMapping("/exists")
public class ExistsController {

    @Autowired
    private RestHighLevelClient client;


    @ApiOperation(value = "检查API是否存在", notes = "")
    @PutMapping("/{index}/{id}")
    @ApiImplicitParam(value = "索引名称", name = "index")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "索引", name = "index"),
            @ApiImplicitParam(value = "文本id", name = "id")
    })
    public boolean sourceType1(@PathVariable String index, @PathVariable("id") String id) throws Exception {
        GetRequest request = new GetRequest(index,id);
        request.fetchSourceContext(new FetchSourceContext(false));
        request.storedFields("_none_");
        request.storedFields("_none_");
        boolean exists = client.exists(request, RequestOptions.DEFAULT);
        return  exists;
    }

}

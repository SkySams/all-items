package org.example.controller.index;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.ShardsAcknowledgedResponse;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.FreezeIndexRequest;
import org.elasticsearch.client.indices.UnfreezeIndexRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zyh
 * @date: 2022/12/9
 */
@Api(tags = "冻结索引")
@RestController
@RequestMapping("/index")
public class IndexFreezeController {

    @Autowired
    private RestHighLevelClient client;

    @ApiOperation("冻结索引")
    @PutMapping("/freeze/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index",dataTypeClass = String.class)
    public ShardsAcknowledgedResponse freeze(@PathVariable String index) throws Exception {
        FreezeIndexRequest request = new FreezeIndexRequest(index);
        ShardsAcknowledgedResponse openIndexResponse = client.indices().freeze(request, RequestOptions.DEFAULT);
        return openIndexResponse;
    }

    @ApiOperation("解冻索引")
    @PutMapping("/unfreeze /{index}")
    @ApiImplicitParam(value = "索引名称", name = "index",dataTypeClass = String.class)
    public ShardsAcknowledgedResponse unfreeze(@PathVariable String index) throws Exception {
        UnfreezeIndexRequest request = new UnfreezeIndexRequest(index);
        ShardsAcknowledgedResponse openIndexResponse = client.indices().unfreeze(request, RequestOptions.DEFAULT);
        return openIndexResponse;
    }


}

package org.example.controller.index;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.admin.indices.alias.IndicesAliasesRequest;
import org.elasticsearch.action.admin.indices.alias.get.GetAliasesRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.GetAliasesResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.DeleteAliasRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: zyh
 * @date: 2022/12/9
 */
@Api(tags = "索引别名")
@RestController
@RequestMapping("/index/aliases")
public class IndexAliasesController {

    @Autowired
    private RestHighLevelClient client;

    @ApiOperation("索引别名")
    @PutMapping("/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index")
    public AcknowledgedResponse indexAliases(@PathVariable String index, @RequestParam String aliaseName) throws Exception {
        IndicesAliasesRequest request = new IndicesAliasesRequest();
        IndicesAliasesRequest.AliasActions aliasAction = new IndicesAliasesRequest.AliasActions(IndicesAliasesRequest.AliasActions.Type.ADD)
                .index(index)
                .alias(aliaseName);
        request.addAliasAction(aliasAction);
        AcknowledgedResponse indicesAliasesResponse = client.indices().updateAliases(request, RequestOptions.DEFAULT);
        return indicesAliasesResponse;
    }

    @ApiOperation("获取别名")
    @GetMapping("/{aliaseName}")
    @ApiImplicitParam(value = "索引别名", name = "aliaseName")
    public GetAliasesResponse getAliases(@PathVariable String aliaseName) throws Exception {
//        GetAliasesRequest request = new GetAliasesRequest();
//        GetAliasesRequest requestWithAliases = new GetAliasesRequest(new String[]{"alias1", "alias2"});
        GetAliasesRequest request = new GetAliasesRequest(aliaseName);
        GetAliasesResponse response = client.indices().getAlias(request, RequestOptions.DEFAULT);
        return response;
    }


    @ApiOperation("删除别名")
    @DeleteMapping("/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index")
    public org.elasticsearch.client.core.AcknowledgedResponse deletedAliases(@PathVariable String index, @RequestParam String aliaseName) throws Exception {
        DeleteAliasRequest request = new DeleteAliasRequest(index, aliaseName);
        org.elasticsearch.client.core.AcknowledgedResponse deleteAliasResponse = client.indices().deleteAlias(request, RequestOptions.DEFAULT);
        return deleteAliasResponse;
    }

    @ApiOperation("索引别名")
    @GetMapping("/exists/{aliaseName}")
    @ApiImplicitParam(value = "索引别名", name = "aliaseName")
    public boolean exists(@PathVariable String aliaseName) throws Exception {
        GetAliasesRequest request = new GetAliasesRequest(aliaseName);
//        GetAliasesRequest request = new GetAliasesRequest();
//        GetAliasesRequest requestWithAliases = new GetAliasesRequest(new String[]{"alias1", "alias2"});
        boolean exists = client.indices().existsAlias(request, RequestOptions.DEFAULT);
        return exists;
    }




}

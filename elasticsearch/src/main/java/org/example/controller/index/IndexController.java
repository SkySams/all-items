package org.example.controller.index;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: zyh
 * @date: 2022/12/2
 */
@Api(tags = "INDEX")
@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @ApiOperation("创建索引")
    @PutMapping("/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index")
    public CreateIndexResponse create(@PathVariable String index) throws Exception {
        // 创建索引请求
        CreateIndexRequest request = new CreateIndexRequest(index);
        CreateIndexResponse response = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        boolean acknowledged = response.isAcknowledged();
        System.out.println(acknowledged);
        return response;
    }

    @ApiOperation("查看索引")
    @GetMapping("/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index")
    public Object viewIndex(@PathVariable("index") String index) throws Exception {
        GetIndexRequest request = new GetIndexRequest(index);
        GetIndexResponse response = restHighLevelClient.indices().get(request, RequestOptions.DEFAULT);
        System.out.println("aliases:" + response.getAliases());
        System.out.println("mappings:" + response.getMappings());
        System.out.println("settings:" + response.getSettings());
        return response.getMappings();
    }

    @ApiOperation("删除索引")
    @DeleteMapping("/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index")
    public AcknowledgedResponse deleted(@PathVariable("index") String index) throws Exception {
        DeleteIndexRequest request = new DeleteIndexRequest(index);
        AcknowledgedResponse response = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
        return response;
    }

}

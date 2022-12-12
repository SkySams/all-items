package org.example.controller.index;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: zyh
 * @date: 2022/12/9
 */
@Api(tags = "映射14")
@RestController
@RequestMapping("/index/mapping")
public class IndexMappingController {

    @Autowired
    private RestHighLevelClient client;

    @ApiOperation("更改映射")
    @PostMapping("/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index",dataTypeClass = String.class)
    public AcknowledgedResponse updateMapping(@PathVariable String index) throws Exception {
        PutMappingRequest request = new PutMappingRequest(index);
        Map<String, Object> jsonMap = new HashMap<>();
        Map<String, Object> message = new HashMap<>();
        message.put("type", "text");
        Map<String, Object> properties = new HashMap<>();
        properties.put("message", message);
        jsonMap.put("properties", properties);
        request.source(jsonMap);
        AcknowledgedResponse putMappingResponse = client.indices().putMapping(request, RequestOptions.DEFAULT);
        return putMappingResponse;
    }

    @ApiOperation("获取映射")
    @GetMapping("/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index",dataTypeClass = String.class)
    public GetMappingsResponse getMapping(@PathVariable String index) throws Exception {
        GetMappingsRequest request = new GetMappingsRequest().indices(index);
        GetMappingsResponse getMappingResponse = client.indices().getMapping(request, RequestOptions.DEFAULT);
        return getMappingResponse;
    }

    @ApiOperation("获取映射字段")
    @GetMapping("/field/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index",dataTypeClass = String.class)
    public GetFieldMappingsResponse getMappingField(@PathVariable String index,@RequestParam String key,@RequestParam String value) throws Exception {
        GetFieldMappingsRequest request = new GetFieldMappingsRequest();
        request.indices(index);
        request.fields(key, value);
        GetFieldMappingsResponse response = client.indices().getFieldMapping(request, RequestOptions.DEFAULT);
        return response;
    }


}

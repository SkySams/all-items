package org.example.controller.index;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.admin.indices.settings.get.GetSettingsRequest;
import org.elasticsearch.action.admin.indices.settings.get.GetSettingsResponse;
import org.elasticsearch.action.admin.indices.settings.put.UpdateSettingsRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: zyh
 * @date: 2022/12/9
 */
@Api(tags = "索引设置")
@RestController
@RequestMapping("/index/settings")
public class IndecSettingsController {

    @Autowired
    private RestHighLevelClient client;

    @ApiOperation("索引设置")
    @PutMapping("/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index",dataTypeClass = String.class)
    public AcknowledgedResponse indexSettings(@PathVariable String index) throws Exception {
        UpdateSettingsRequest request = new UpdateSettingsRequest(index);
//        UpdateSettingsRequest requestMultiple = new UpdateSettingsRequest("index1", "index2");
//        UpdateSettingsRequest requestAll = new UpdateSettingsRequest();
        String settingKey = "index.number_of_replicas";
        int settingValue = 0;
        Settings settings = Settings.builder().put(settingKey, settingValue).build();
        request.settings(settings);

//        request.settings("{\"index.number_of_replicas\": \"2\"}", XContentType.JSON);
//        Map<String, Object> map = new HashMap<>();
//        map.put(settingKey, settingValue);
//        request.settings(map);

        AcknowledgedResponse updateSettingsResponse = client.indices().putSettings(request, RequestOptions.DEFAULT);
        return updateSettingsResponse;
    }
    @ApiOperation("索引设置")
    @GetMapping("/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index",dataTypeClass = String.class)
    public GetSettingsResponse getIndexSettings(@PathVariable String index) throws Exception {
        GetSettingsRequest request = new GetSettingsRequest().indices(index);
        GetSettingsResponse response = client.indices().getSettings(request, RequestOptions.DEFAULT);
        return response;
    }

}

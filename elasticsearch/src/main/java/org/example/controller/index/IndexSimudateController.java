package org.example.controller.index;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetComposableIndexTemplatesResponse;
import org.elasticsearch.client.indices.PutComposableIndexTemplateRequest;
import org.elasticsearch.client.indices.SimulateIndexTemplateRequest;
import org.elasticsearch.client.indices.SimulateIndexTemplateResponse;
import org.elasticsearch.cluster.metadata.ComposableIndexTemplate;
import org.elasticsearch.cluster.metadata.Template;
import org.elasticsearch.common.settings.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @author: zyh
 * @date: 2022/12/10
 */
@Api(tags = "模拟索引")
@RestController
@RequestMapping("/index/simudate")
public class IndexSimudateController {

    @Autowired
    private RestHighLevelClient client;

    @ApiOperation("模拟索引")
    @PostMapping("/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index",dataTypeClass = String.class)
    public SimulateIndexTemplateResponse createdComposable(@PathVariable String index) throws Exception {
        SimulateIndexTemplateRequest simulateRequest = new SimulateIndexTemplateRequest(index);
        PutComposableIndexTemplateRequest newIndexTemplateRequest = new PutComposableIndexTemplateRequest().name("used-for-simulation");
        Settings settings = Settings.builder().put("index.number_of_shards", 6).build();
        Template template = new Template(settings, null, null);
        ComposableIndexTemplate composableIndexTemplate = new ComposableIndexTemplate(Arrays.asList("log-*"),
                template, null, 90L, null, null);
        newIndexTemplateRequest.indexTemplate(composableIndexTemplate);
        simulateRequest.indexTemplateV2Request(newIndexTemplateRequest);
        SimulateIndexTemplateResponse response = client.indices().simulateIndexTemplate(simulateRequest, RequestOptions.DEFAULT);
        return response;
    }
}

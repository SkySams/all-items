package org.example.controller.index;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.admin.indices.template.delete.DeleteIndexTemplateRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.*;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * @author: zyh
 * @date: 2022/12/9
 */
@Api(tags = "索引模块")
@RestController
@RequestMapping("/index/template")
public class IndexTemplateController {


//    Map<String, Object> jsonMap = new HashMap<>();
//    {
//        Map<String, Object> properties = new HashMap<>();
//        {
//            Map<String, Object> message = new HashMap<>();
//            message.put("type", "text");
//            properties.put("message", message);
//        }
//        jsonMap.put("properties", properties);
//    }
//request.mapping(jsonMap);

//    XContentBuilder builder = XContentFactory.jsonBuilder();
//builder.startObject();
//
//    {
//        builder.startObject("properties");
//        {
//            builder.startObject("message");
//            {
//                builder.field("type", "text");
//            }
//            builder.endObject();
//        }
//        builder.endObject();
//    }
//builder.endObject();
//request.mapping(builder);


    @Autowired
    private RestHighLevelClient client;

    @ApiOperation("索引模块")
    @PutMapping("/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index",dataTypeClass = String.class)
    public AcknowledgedResponse indexTemplaste(@PathVariable String index) throws Exception {
        PutIndexTemplateRequest request = new PutIndexTemplateRequest(index);
        request.patterns(Arrays.asList("pattern-1", "log-*"));
        request.settings(Settings.builder()
                .put("index.number_of_shards", 3)
                .put("index.number_of_replicas", 1)
        );
        AcknowledgedResponse putTemplateResponse = client.indices().putTemplate(request, RequestOptions.DEFAULT);
        return putTemplateResponse;
    }

    @ApiOperation("获取索引模块")
    @GetMapping("/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index",dataTypeClass = String.class)
    public GetIndexTemplatesResponse getIndexTemplate(@PathVariable String index) throws Exception {
        GetIndexTemplatesRequest request = new GetIndexTemplatesRequest(index);
//        GetIndexTemplatesRequest request = new GetIndexTemplatesRequest("template-1", "template-2");
//        GetIndexTemplatesRequest request = new GetIndexTemplatesRequest("my-*");
        GetIndexTemplatesResponse response = client.indices().getIndexTemplate(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation("检验索引模块")
    @GetMapping("/exists/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index",dataTypeClass = String.class)
    public boolean existsIndexTemplate(@PathVariable String index) throws Exception {
        IndexTemplatesExistRequest request;
        request = new IndexTemplatesExistRequest(index);
//        request = new IndexTemplatesExistRequest("template-1", "template-2");
//        request = new IndexTemplatesExistRequest("my-*");
        request.setLocal(true);
        request.setMasterNodeTimeout(TimeValue.timeValueMinutes(1));
        request.setMasterNodeTimeout("1m");

        boolean exists = client.indices().existsTemplate(request, RequestOptions.DEFAULT);
        return exists;
    }

    @ApiOperation("删除")
    @DeleteMapping("/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index",dataTypeClass = String.class)
    public AcknowledgedResponse delete(@PathVariable String index) throws Exception {
        DeleteIndexTemplateRequest request = new DeleteIndexTemplateRequest();
        request.name(index);
        AcknowledgedResponse deleteTemplateAcknowledge = client.indices().deleteTemplate(request, RequestOptions.DEFAULT);
        return deleteTemplateAcknowledge;
    }





}

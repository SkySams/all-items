package org.example.controller.index;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetComposableIndexTemplateRequest;
import org.elasticsearch.client.indices.GetComposableIndexTemplatesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zyh
 * @date: 2022/12/9
 */
@Api(tags = "组合索引")
@RestController
@RequestMapping("/index/composable")
public class IndexComposableTemplateController {

    @Autowired
    private RestHighLevelClient client;

    @ApiOperation("组合")
    @DeleteMapping("/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index")
    public GetComposableIndexTemplatesResponse composable(@PathVariable String index) throws Exception {
        GetComposableIndexTemplateRequest request = new GetComposableIndexTemplateRequest(index);
//        request = new GetComposableIndexTemplateRequest("my-*");
        GetComposableIndexTemplatesResponse getTemplatesResponse = client.indices().getIndexTemplate(request, RequestOptions.DEFAULT);
        return getTemplatesResponse;
    }

}

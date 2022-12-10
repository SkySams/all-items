package org.example.controller.index;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.DeleteComposableIndexTemplateRequest;
import org.elasticsearch.client.indices.GetComposableIndexTemplateRequest;
import org.elasticsearch.client.indices.GetComposableIndexTemplatesResponse;
import org.elasticsearch.client.indices.PutComposableIndexTemplateRequest;
import org.elasticsearch.cluster.metadata.AliasMetadata;
import org.elasticsearch.cluster.metadata.ComposableIndexTemplate;
import org.elasticsearch.cluster.metadata.Template;
import org.elasticsearch.common.compress.CompressedXContent;
import org.elasticsearch.common.settings.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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

    @ApiOperation("创建组合")
    @PostMapping ("/{name}")
    @ApiImplicitParam(value = "名称", name = "name")
    public GetComposableIndexTemplatesResponse createdComposable(@PathVariable String name) throws Exception {
        GetComposableIndexTemplateRequest request = new GetComposableIndexTemplateRequest(name);
//        request = new GetComposableIndexTemplateRequest("my-*");
        GetComposableIndexTemplatesResponse getTemplatesResponse = client.indices().getIndexTemplate(request, RequestOptions.DEFAULT);
        return getTemplatesResponse;
    }

    @ApiOperation("更新组合")
    @PutMapping ("/{name}")
    @ApiImplicitParam(value = "名称", name = "name")
    public AcknowledgedResponse updateComposable(@PathVariable String name) throws Exception {
        PutComposableIndexTemplateRequest request = new PutComposableIndexTemplateRequest().name(name);
//       setting
//        Settings settings = Settings.builder()
//                .put("index.number_of_shards", 3)
//                .put("index.number_of_replicas", 1)
//                .build();
//        Template template = new Template(settings, null, null);

        ComposableIndexTemplate composableIndexTemplate =
                new ComposableIndexTemplate(Arrays.asList("pattern-1", "log-*"), null, null, null, null, null);
        request.indexTemplate(composableIndexTemplate);
        AcknowledgedResponse response = client.indices().putIndexTemplate(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation("映射组合")
    @PostMapping ("/mapping/{name}")
    @ApiImplicitParam(value = "名称", name = "name")
    public void mapping(@PathVariable String name) throws Exception{
        String mappingJson = "{\n" +
                "  \"properties\": {\n" +
                "    \"message\": {\n" +
                "      \"type\": \"text\"\n" +
                "    }\n" +
                "  }\n" +
                "}";
        PutComposableIndexTemplateRequest request = new PutComposableIndexTemplateRequest()
                .name("my-template");
        Template template = new Template(null, new CompressedXContent(mappingJson), null);
        ComposableIndexTemplate composableIndexTemplate = new ComposableIndexTemplate(Arrays.asList("pattern-1", "log-*"),
                template, null, null, null, null);
        request.indexTemplate(composableIndexTemplate);
    }

    private void aliases (){
        PutComposableIndexTemplateRequest request = new PutComposableIndexTemplateRequest().name("my-template");
        AliasMetadata twitterAlias = AliasMetadata.builder("twitter_alias").build();
        AliasMetadata placeholderAlias = AliasMetadata.builder("{index}_alias").searchRouting("xyz").build();
        Map<String, AliasMetadata> aliases = new HashMap<>();
        aliases.put("twitter_alias", twitterAlias);
        aliases.put("{index}_alias", placeholderAlias);
        Template template = new Template(null, null, aliases);
        ComposableIndexTemplate composableIndexTemplate = new ComposableIndexTemplate(Arrays.asList("pattern-1", "log-*"),
                template, null, null, null, null);
        request.indexTemplate(composableIndexTemplate);
    }

    private void composableTemplate(){
        PutComposableIndexTemplateRequest request = new PutComposableIndexTemplateRequest()
                .name("my-template");
        ComposableIndexTemplate composableIndexTemplate =
                new ComposableIndexTemplate(Arrays.asList("pattern-1", "log-*"), null,
                        Collections.singletonList("ct1"), null, null, null);
        request.indexTemplate(composableIndexTemplate);
    }

    private void prioroty(){
        PutComposableIndexTemplateRequest request = new PutComposableIndexTemplateRequest().name("my-template");
        ComposableIndexTemplate composableIndexTemplate = new ComposableIndexTemplate(Arrays.asList("pattern-1", "log-*"),
                null, null, 20L, null, null);
        request.indexTemplate(composableIndexTemplate);
    }

    private void version(){
        PutComposableIndexTemplateRequest request = new PutComposableIndexTemplateRequest()
                .name("my-template");
        ComposableIndexTemplate composableIndexTemplate = new ComposableIndexTemplate(Arrays.asList("pattern-1", "log-*"),
                null, null, null, 3L, null);
        request.indexTemplate(composableIndexTemplate);
    }

    @ApiOperation("删除组合")
    @DeleteMapping ("/{name}")
    @ApiImplicitParam(value = "名称", name = "name")
    public AcknowledgedResponse deleted(@PathVariable String name) throws Exception{
        DeleteComposableIndexTemplateRequest request = new DeleteComposableIndexTemplateRequest(name);
        AcknowledgedResponse response = client.indices().deleteIndexTemplate(request, RequestOptions.DEFAULT);
        return response;
    }

}

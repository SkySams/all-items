package org.example.controller.doc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.VersionType;
import org.elasticsearch.rest.RestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: zyh
 * @date: 2022/12/6
 */
@Api(tags = "POSTS")
@RestController
@RequestMapping("/index/posts")
public class PostsController {

    @Autowired
    private RestHighLevelClient client;

    @ApiOperation(value = "创建文本", notes = "没有索引创建,ES会自动创建，类型es7以上没有自定义类型，只有_doc类型")
    @PutMapping("/{index}/{id}")
    @ApiImplicitParam(value = "索引名称", name = "index")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "索引", name = "index"),
            @ApiImplicitParam(value = "文本id", name = "id")
    })
    public IndexResponse createIndexPosts(@PathVariable String index, @PathVariable("id") String id) throws Exception {
        IndexRequest request = new IndexRequest(index);
        request.id(id);
        String jsonString = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
        request.source(jsonString, XContentType.JSON);
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "创建文本MAP", notes = "没有索引创建,ES会自动创建，类型es7以上没有自定义类型，只有_doc类型")
    @PutMapping("/map/{index}/{id}")
    @ApiImplicitParam(value = "索引名称", name = "index")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "索引", name = "index"),
            @ApiImplicitParam(value = "文本id", name = "id")
    })
    public IndexResponse createIndexMap(@PathVariable String index, @PathVariable("id") String id, @RequestBody Map map) throws Exception {
        IndexRequest request = new IndexRequest(index).id(id);
        request.source(map);
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "创建文本MAP", notes = "没有索引创建,ES会自动创建，类型es7以上没有自定义类型，只有_doc类型")
    @PutMapping("/map/low/{index}/{id}")
    @ApiImplicitParam(value = "索引名称", name = "index")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "索引", name = "index"),
            @ApiImplicitParam(value = "文本id", name = "id")
    })
    public IndexResponse createIndexMapLow(@PathVariable String index, @PathVariable("id") String id) throws Exception {

        Map<String, Object> map = new HashMap<>();
        map.put("name", "cwsky");
        map.put("postsDate", new Date());
        map.put("message", "Elasticsearch");
        IndexRequest request = new IndexRequest(index).id(id);
        request.source(map);
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "创建文本(类型3)", notes = "没有索引创建,ES会自动创建，类型es7以上没有自定义类型，只有_doc类型")
    @PutMapping("/builder/{index}/{id}")
    @ApiImplicitParam(value = "索引名称", name = "index")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "索引", name = "index"),
            @ApiImplicitParam(value = "文本id", name = "id")
    })
    public IndexResponse createXContentBuilder(@PathVariable String index, @PathVariable("id") String id) throws Exception {
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.field("name", "kimchy");
            builder.timeField("postDate", new Date());
            builder.field("message", "trying out Elasticsearch");
        }
        builder.endObject();
        IndexRequest request = new IndexRequest(index).id(id).source(builder);
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "创建文本(类型4)", notes = "没有索引创建,ES会自动创建，类型es7以上没有自定义类型，只有_doc类型")
    @PutMapping("/type4/{index}/{id}")
    @ApiImplicitParam(value = "索引名称", name = "index")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "索引", name = "index"),
            @ApiImplicitParam(value = "文本id", name = "id")
    })
    public IndexResponse createType4(@PathVariable String index, @PathVariable("id") String id) throws Exception {
        IndexRequest request = new IndexRequest(index).id(id).source("name","Jack","postsDate",new Date(),
                "message","Elasticsearch");
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);

        String responseIndex = response.getIndex();
        String responseId = response.getId();
        if (response.getResult() == DocWriteResponse.Result.CREATED) {

        } else if (response.getResult() == DocWriteResponse.Result.UPDATED) {

        }
        ReplicationResponse.ShardInfo shardInfo = response.getShardInfo();
        if (shardInfo.getTotal() != shardInfo.getSuccessful()) {

        }
        if (shardInfo.getFailed() > 0) {
            for (ReplicationResponse.ShardInfo.Failure failure : shardInfo.getFailures()) {
                String reason = failure.reason();
                System.out.println(reason);
            }
        }

        return response;
    }

    @ApiOperation(value = "创建文本(类型5)", notes = "没有索引创建,ES会自动创建，类型es7以上没有自定义类型，只有_doc类型")
    @PutMapping("/type5/{index}/{id}")
    @ApiImplicitParam(value = "索引名称", name = "index")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "索引", name = "index"),
            @ApiImplicitParam(value = "文本id", name = "id")
    })
    public IndexResponse createType5(@PathVariable String index, @PathVariable("id") String id)throws IOException{
        IndexRequest request = new IndexRequest(index).id(id).source("field","value")
                .setIfSeqNo(10L).setIfPrimaryTerm(20);
        IndexResponse response = null;
        try {
             response = client.index(request, RequestOptions.DEFAULT);
        } catch( ElasticsearchException e) {
            if (e.status() == RestStatus.CONFLICT) {

            }
        }
        return response;
    }

    @ApiOperation(value = "创建文本(类型6)", notes = "没有索引创建,ES会自动创建，类型es7以上没有自定义类型，只有_doc类型")
    @PutMapping("/type6/{index}/{id}")
    @ApiImplicitParam(value = "索引名称", name = "index")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "索引", name = "index"),
            @ApiImplicitParam(value = "文本id", name = "id")
    })
    public IndexResponse createType6(@PathVariable String index, @PathVariable("id") String id)throws IOException{
        IndexRequest request = new IndexRequest(index).id(id).source("field","value")
                .opType(DocWriteRequest.OpType.CREATE);
        IndexResponse response = null;
        try {
            response = client.index(request, RequestOptions.DEFAULT);
        } catch( ElasticsearchException e) {
            if (e.status() == RestStatus.CONFLICT) {

            }
        }
        return response;
    }



    private void create()throws Exception{
        IndexRequest request = new IndexRequest("").id("");
        request.routing("routing");
        request.timeout(TimeValue.timeValueSeconds(1));
        request.timeout("1s");

        request.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
        request.setRefreshPolicy("wait_for");

        request.version(2);

        request.versionType(VersionType.EXTERNAL);

        request.opType(DocWriteRequest.OpType.CREATE);
        request.opType("create");

        request.setPipeline("pipeline");


    }




}

package org.example.controller.index;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.admin.indices.cache.clear.ClearIndicesCacheRequest;
import org.elasticsearch.action.admin.indices.cache.clear.ClearIndicesCacheResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.flush.FlushRequest;
import org.elasticsearch.action.admin.indices.flush.FlushResponse;
import org.elasticsearch.action.admin.indices.flush.SyncedFlushRequest;
import org.elasticsearch.action.admin.indices.forcemerge.ForceMergeRequest;
import org.elasticsearch.action.admin.indices.forcemerge.ForceMergeResponse;
import org.elasticsearch.action.admin.indices.open.OpenIndexRequest;
import org.elasticsearch.action.admin.indices.open.OpenIndexResponse;
import org.elasticsearch.action.admin.indices.refresh.RefreshRequest;
import org.elasticsearch.action.admin.indices.refresh.RefreshResponse;
import org.elasticsearch.action.admin.indices.rollover.RolloverRequest;
import org.elasticsearch.action.admin.indices.rollover.RolloverResponse;
import org.elasticsearch.action.support.ActiveShardCount;
import org.elasticsearch.action.support.DefaultShardOperationFailedException;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.SyncedFlushResponse;
import org.elasticsearch.client.indices.*;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author: zyh
 * @date: 2022/12/9
 */
@Api(tags = "INDEX14")
@RestController
@RequestMapping("/index14")
public class Index14Controller {

    @Autowired
    private RestHighLevelClient client;

    @ApiOperation("创建索引json")
    @PutMapping("/json/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index")
    public CreateIndexResponse createJson(@PathVariable String index) throws Exception {
        CreateIndexRequest request = new CreateIndexRequest(index);
        // Index settings
        request.settings(Settings.builder()
                .put("index.number_of_shards", 1)
                .put("index.number_of_replicas", 1)
        );
        // index mappings
        request.mapping(
                "{\n" +
                        "  \"properties\": {\n" +
                        "    \"message\": {\n" +
                        "      \"type\": \"text\"\n" +
                        "    }\n" +
                        "  }\n" +
                        "}",
                XContentType.JSON);
        // settings
        this.createSource(request);
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation("创建索引Map")
    @PutMapping("/map/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index")
    public CreateIndexResponse createMap(@PathVariable String index) throws Exception {
        CreateIndexRequest request = new CreateIndexRequest(index);
        Map<String, Object> message = new HashMap<>();
        message.put("type", "text");
        Map<String, Object> properties = new HashMap<>();
        properties.put("message", message);
        Map<String, Object> mapping = new HashMap<>();
        mapping.put("properties", properties);
        request.mapping(mapping);
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
        ;
        return response;
    }

    @ApiOperation("创建索引XC")
    @PutMapping("/xc/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index")
    public CreateIndexResponse createXC(@PathVariable String index) throws Exception {
        CreateIndexRequest request = new CreateIndexRequest(index);
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.startObject("properties");
            {
                builder.startObject("message");
                {
                    builder.field("type", "text");
                }
                builder.endObject();
            }
            builder.endObject();
        }
        builder.endObject();
        request.mapping(builder);
        // 别名
//        request.alias(new Alias("twitter_alias").filter(QueryBuilders.termQuery("user", "kimchy")));
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
        ;
        return response;
    }

    @ApiOperation("删除索引")
    @DeleteMapping("/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index")
    public AcknowledgedResponse deleted(@PathVariable String index) throws Exception {
        DeleteIndexRequest request = new DeleteIndexRequest(index);
        // 设置超时时间
        request.timeout(TimeValue.timeValueMinutes(2));
        // 节点超过时间
        request.masterNodeTimeout(TimeValue.timeValueMinutes(2));
        request.indicesOptions(IndicesOptions.lenientExpandOpen());
        AcknowledgedResponse response = client.indices().delete(request,RequestOptions.DEFAULT);
        boolean acknowledged = response.isAcknowledged();
        System.out.println(acknowledged);
        return response;
    }

    @ApiOperation(value = "检测索引",notes = "索引是否存在")
    @GetMapping("/exists/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index")
    public boolean exists (@PathVariable String index) throws Exception {
        GetIndexRequest request = new GetIndexRequest(index);
        request.local(false);
        request.humanReadable(true);
        request.includeDefaults(false);
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        return exists;
    }

    @ApiOperation(value = "打开索引",notes = "打开索引")
    @PutMapping("/open/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index")
    public OpenIndexResponse open (@PathVariable String index) throws Exception {
        OpenIndexRequest request = new OpenIndexRequest(index);
        request.timeout(TimeValue.timeValueMinutes(2));
        request.masterNodeTimeout(TimeValue.timeValueMinutes(1));
        request.waitForActiveShards(ActiveShardCount.DEFAULT);
        request.indicesOptions(IndicesOptions.strictExpandOpen());
        OpenIndexResponse openIndexResponse = client.indices().open(request, RequestOptions.DEFAULT);
        boolean acknowledged = openIndexResponse.isAcknowledged();
        boolean shardsAcked = openIndexResponse.isShardsAcknowledged();
        return openIndexResponse;
    }

    @ApiOperation(value = "关闭索引",notes = "关闭索引")
    @PutMapping("/close/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index")
    public AcknowledgedResponse close (@PathVariable String index) throws Exception {
        CloseIndexRequest request = new CloseIndexRequest(index);
        request.setTimeout(TimeValue.timeValueMinutes(2));
        request.setMasterTimeout(TimeValue.timeValueMinutes(1));
        request.indicesOptions(IndicesOptions.lenientExpandOpen());
        AcknowledgedResponse closeIndexResponse = client.indices().close(request, RequestOptions.DEFAULT);
        boolean acknowledged = closeIndexResponse.isAcknowledged();
        return closeIndexResponse;
    }

    @ApiOperation(value = "更改索引名称",notes = "更改索引名称")
    @PutMapping("/shrink/{target_index}/{source_index}")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "目标索引名称", name = "target_index"),
            @ApiImplicitParam(value = "源索引名称", name = "source_index")
    })
    public ResizeResponse shrink (@PathVariable String target_index,@PathVariable String source_index) throws Exception {
        ResizeRequest request = new ResizeRequest(target_index,source_index);
        request.setMaxPrimaryShardSize(new ByteSizeValue(50, ByteSizeUnit.GB));
        ResizeResponse resizeResponse = client.indices().shrink(request, RequestOptions.DEFAULT);
        return resizeResponse;
    }

    @ApiOperation(value = "刷新索引",notes = "刷新索引")
    @PutMapping("/refresh/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index")
    public RefreshResponse refresh (@PathVariable String index) throws Exception {
        RefreshRequest request = new RefreshRequest(index);
//        RefreshRequest requestMultiple = new RefreshRequest("index1", "index2");
//        RefreshRequest requestAll = new RefreshRequest();
        request.indicesOptions(IndicesOptions.lenientExpandOpen());
        RefreshResponse refreshResponse = client.indices().refresh(request, RequestOptions.DEFAULT);
        int totalShards = refreshResponse.getTotalShards();
        int successfulShards = refreshResponse.getSuccessfulShards();
        int failedShards = refreshResponse.getFailedShards();
        DefaultShardOperationFailedException[] failures = refreshResponse.getShardFailures();
        return refreshResponse;
    }

    @ApiOperation(value = "flush索引",notes = "flush索引")
    @PutMapping("/flush/synced/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index")
    public SyncedFlushResponse flushSynced (@PathVariable String index) throws Exception {
        SyncedFlushRequest request = new SyncedFlushRequest(index);
//        SyncedFlushRequest requestMultiple = new SyncedFlushRequest("index1", "index2");
//        SyncedFlushRequest requestAll = new SyncedFlushRequest();
        request.indicesOptions(IndicesOptions.lenientExpandOpen());
        SyncedFlushResponse flushSyncedResponse = client.indices().flushSynced(request, RequestOptions.DEFAULT);
        int totalShards = flushSyncedResponse.totalShards();
        int successfulShards = flushSyncedResponse.successfulShards();
        int failedShards = flushSyncedResponse.failedShards();

        for (Map.Entry<String, SyncedFlushResponse.IndexResult> responsePerIndexEntry:
                flushSyncedResponse.getIndexResults().entrySet()) {
            String indexName = responsePerIndexEntry.getKey();
            SyncedFlushResponse.IndexResult indexResult = responsePerIndexEntry.getValue();
            int totalShardsForIndex = indexResult.totalShards();
            int successfulShardsForIndex = indexResult.successfulShards();
            int failedShardsForIndex = indexResult.failedShards();
            if (failedShardsForIndex > 0) {
                for (SyncedFlushResponse.ShardFailure failureEntry: indexResult.failures()) {
                    int shardId = failureEntry.getShardId();
                    String failureReason = failureEntry.getFailureReason();
                    Map<String, Object> routing = failureEntry.getRouting();
                }
            }
        }
        return flushSyncedResponse;
    }

    @ApiOperation(value = "清除缓存",notes = "清除缓存")
    @PutMapping("/clear/cache/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index")
    public ClearIndicesCacheResponse clearCache (@PathVariable String index) throws Exception {
        ClearIndicesCacheRequest request = new ClearIndicesCacheRequest(index);
//        ClearIndicesCacheRequest requestMultiple = new ClearIndicesCacheRequest("index1", "index2");
//        ClearIndicesCacheRequest requestAll = new ClearIndicesCacheRequest();
        request.indicesOptions(IndicesOptions.lenientExpandOpen());
        request.queryCache(true);
        request.fieldDataCache(true);
        request.requestCache(true);
//        request.fields("field1", "field2", "field3");
        ClearIndicesCacheResponse clearCacheResponse = client.indices().clearCache(request, RequestOptions.DEFAULT);
        return clearCacheResponse;
    }

    @ApiOperation(value = "强制合并",notes = "强制合并")
    @PutMapping("/force/merge/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index")
    public ForceMergeResponse forceMerge (@PathVariable String index) throws Exception {
        ForceMergeRequest request = new ForceMergeRequest("index1");
//        ForceMergeRequest requestMultiple = new ForceMergeRequest("index1", "index2");
//        ForceMergeRequest requestAll = new ForceMergeRequest();
        request.indicesOptions(IndicesOptions.lenientExpandOpen());
        request.maxNumSegments(1);
        request.onlyExpungeDeletes(true);
        request.flush(true);
        ForceMergeResponse forceMergeResponse = client.indices().forcemerge(request, RequestOptions.DEFAULT);
        return forceMergeResponse;
    }


    @ApiOperation(value = "Rollover",notes = "Rollover")
    @PutMapping("/rollover/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index")
    public RolloverResponse rollover (@PathVariable String index) throws Exception {
        RolloverRequest request = new RolloverRequest("alias", "index-2");
        request.addMaxIndexAgeCondition(new TimeValue(7, TimeUnit.DAYS));
        request.addMaxIndexDocsCondition(1000);
        request.addMaxIndexSizeCondition(new ByteSizeValue(5, ByteSizeUnit.GB));
        request.addMaxPrimaryShardSizeCondition(new ByteSizeValue(2, ByteSizeUnit.GB));
        RolloverResponse rolloverResponse = client.indices().rollover(request, RequestOptions.DEFAULT);
        return rolloverResponse;
    }


    public CreateIndexResponse createSource( CreateIndexRequest request) throws Exception{
        // 创建索引超时时间
        request.setTimeout(TimeValue.timeValueMinutes(2));
        request.setMasterTimeout(TimeValue.timeValueMinutes(1));
        request.waitForActiveShards(ActiveShardCount.from(2));
        request.waitForActiveShards(ActiveShardCount.DEFAULT);
        CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
        boolean acknowledged = createIndexResponse.isAcknowledged();
        boolean shardsAcknowledged = createIndexResponse.isShardsAcknowledged();
        return createIndexResponse;
    }

}

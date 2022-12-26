package org.example.controller.rollup;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.RollupClient;
import org.elasticsearch.client.core.AcknowledgedResponse;
import org.elasticsearch.client.rollup.*;
import org.elasticsearch.client.rollup.job.config.RollupJobConfig;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.search.aggregations.metrics.MaxAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: zyh
 * @date: 2022/12/10
 */
@Api(tags = "汇总")
@RestController
@RequestMapping("/rollup")
public class RollupController {

    @Autowired
    private RestHighLevelClient client;

    @ApiOperation(value = "创建")
    @PostMapping()
    public AcknowledgedResponse createRollup(@RequestParam String id) throws Exception {

        RollupJobConfig config = new RollupJobConfig(id, "", "", "",
                10, null, null, TimeValue.timeValueMinutes(2));
        PutRollupJobRequest request = new PutRollupJobRequest(config);
        AcknowledgedResponse response = client.rollup().putRollupJob(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "获取")
    @GetMapping()
    public GetRollupJobResponse getRollup(@RequestParam String id) throws Exception {
        GetRollupJobRequest getJob = new GetRollupJobRequest(id);
        GetRollupJobResponse response = client.rollup().getRollupJob(getJob, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "开启")
    @GetMapping("/start")
    public StartRollupJobResponse startRollup(@RequestParam String id) throws Exception {
        StartRollupJobRequest request = new StartRollupJobRequest(id);
        RollupClient rc = client.rollup();
        StartRollupJobResponse response = rc.startRollupJob(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "关闭")
    @GetMapping("stop")
    public StopRollupJobResponse stopRollup(@RequestParam String id) throws Exception {
        StopRollupJobRequest request = new StopRollupJobRequest(id);
        request.waitForCompletion(true);
        request.timeout(TimeValue.timeValueSeconds(10));
        RollupClient rc = client.rollup();
        StopRollupJobResponse response = rc.stopRollupJob(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "删除")
    @DeleteMapping
    public AcknowledgedResponse deletedRollup(@RequestParam String id) throws Exception {
        DeleteRollupJobRequest request = new DeleteRollupJobRequest(id);
        AcknowledgedResponse response = client.rollup().deleteRollupJob(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "搜索")
    @GetMapping("/search")
    public SearchResponse search () throws Exception{
        SearchRequest request = new SearchRequest();
        request.source(new SearchSourceBuilder()
                .size(0)
                .aggregation(new MaxAggregationBuilder("max_temperature")
                        .field("temperature")));
        SearchResponse response =
                client.rollup().search(request, RequestOptions.DEFAULT);
        return response;
    }


}

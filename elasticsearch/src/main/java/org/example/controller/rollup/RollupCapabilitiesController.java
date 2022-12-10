package org.example.controller.rollup;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.AcknowledgedResponse;
import org.elasticsearch.client.rollup.*;
import org.elasticsearch.client.rollup.job.config.RollupJobConfig;
import org.elasticsearch.common.unit.TimeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: zyh
 * @date: 2022/12/10
 */
@Api(tags = "汇总CAPA")
@RestController
@RequestMapping("/rollup/capa")
public class RollupCapabilitiesController {

    @Autowired
    private RestHighLevelClient client;

    @ApiOperation(value = "获取CAPA")
    @GetMapping()
    public GetRollupCapsResponse getRollup(@RequestParam String indexPattern) throws Exception {
        GetRollupCapsRequest getRollupCapsRequest = new GetRollupCapsRequest(indexPattern);
        GetRollupCapsResponse capsResponse = client.rollup().getRollupCapabilities(getRollupCapsRequest, RequestOptions.DEFAULT);
        return capsResponse;
    }

    @ApiOperation(value = "获取CAPA-Index")
    @GetMapping("index")
    public GetRollupIndexCapsResponse getRollupIndex(@RequestParam String index) throws Exception {
        GetRollupIndexCapsRequest request = new GetRollupIndexCapsRequest(index);
        GetRollupIndexCapsResponse response = client.rollup().getRollupIndexCapabilities(request, RequestOptions.DEFAULT);
        return response;
    }


    @ApiOperation(value = "创建CAPA")
    @PostMapping()
    public AcknowledgedResponse createRollup()throws Exception{
        final String indexPattern = "docs";
        final String rollupIndexName = "rollup";
        final String cron = "*/1 * * * * ?";
        final int pageSize = 100;
        final TimeValue timeout = null;

        String id = "job_1";
        RollupJobConfig config = new RollupJobConfig(id, indexPattern, rollupIndexName, cron,
                pageSize, null, null, timeout);

        PutRollupJobRequest request = new PutRollupJobRequest(config);
        AcknowledgedResponse response = client.rollup().putRollupJob(request, RequestOptions.DEFAULT);
        boolean acknowledged = response.isAcknowledged();
        return response;
    }

}

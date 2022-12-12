package org.example.controller.watcher;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.watcher.*;
import org.elasticsearch.common.bytes.BytesArray;
import org.elasticsearch.common.bytes.BytesReference;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: zyh
 * @date: 2022/12/12
 */
@Api(tags = "ES观察者")
@RestController
@RequestMapping("/watch")
public class WatchServiceController {

    @Autowired
    private RestHighLevelClient client;

    @ApiOperation("开启观察者")
    @PutMapping("/start")
    public AcknowledgedResponse startWatch()throws Exception{
        StartWatchServiceRequest request = new StartWatchServiceRequest();
        AcknowledgedResponse response = client.watcher().startWatchService(request, RequestOptions.DEFAULT);
        boolean isAcknowledged = response.isAcknowledged();
        return response;
    }

    @ApiOperation("关闭观察者")
    @PutMapping("/stop")
    public AcknowledgedResponse stopWatch()throws Exception{
        StopWatchServiceRequest request = new StopWatchServiceRequest();
        AcknowledgedResponse response = client.watcher().stopWatchService(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation("创建或更改")
    @PostMapping("/up/or/cre")
    public PutWatchResponse createOrUpdate()throws Exception{
        BytesReference watch = new BytesArray("{ \n" +
                "  \"trigger\": { \"schedule\": { \"interval\": \"10h\" } },\n" +
                "  \"input\": { \"simple\": { \"foo\" : \"bar\" } },\n" +
                "  \"actions\": { \"logme\": { \"logging\": { \"text\": \"{{ctx.payload}}\" } } }\n" +
                "}");
        PutWatchRequest request = new PutWatchRequest("my_watch_id", watch, XContentType.JSON);
        request.setActive(false);
        PutWatchResponse response = client.watcher().putWatch(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation("获取观察者")
    @GetMapping("")
    public GetWatchResponse getWatch(String watchId)throws Exception{
        GetWatchRequest request = new GetWatchRequest(watchId);
        GetWatchResponse response = client.watcher().getWatch(request, RequestOptions.DEFAULT);
        String watchIds = response.getId();
        boolean found = response.isFound();
        long version = response.getVersion();
        WatchStatus status = response.getStatus();
        BytesReference source = response.getSource();
        return response;
    }

    @ApiOperation("删除观察者")
    @DeleteMapping("")
    public DeleteWatchResponse deleted(String watchId)throws Exception{
        DeleteWatchRequest request = new DeleteWatchRequest(watchId);
        DeleteWatchResponse response = client.watcher().deleteWatch(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation("ack观察者")
    @PutMapping("/ack")
    public AckWatchResponse ack(String watchId)throws Exception{
        AckWatchRequest request = new AckWatchRequest(watchId, "logme", "emailme");
        AckWatchResponse response = client.watcher().ackWatch(request, RequestOptions.DEFAULT);
        return response;
    }
    @ApiOperation("停止观察者")
    @PutMapping("/deactivate")
    public DeactivateWatchResponse deactivate(String watchId)throws Exception{
        DeactivateWatchRequest request = new DeactivateWatchRequest("my_watch_id");
        DeactivateWatchResponse response = client.watcher().deactivateWatch(request, RequestOptions.DEFAULT);
        return response;
    }
    @ApiOperation("激活观察者")
    @PutMapping("/activate")
    public ActivateWatchResponse activate(String watchId)throws Exception{
        ActivateWatchRequest request = new ActivateWatchRequest("my_watch_id");
        ActivateWatchResponse response = client.watcher().activateWatch(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation("执行观察者")
    @PutMapping("/execute")
    public ExecuteWatchResponse execute(String watchId)throws Exception{
        ExecuteWatchRequest request = ExecuteWatchRequest.byId("my_watch_id");
        request.setAlternativeInput("{ \"foo\" : \"bar\" }");
        request.setActionMode("action1", ExecuteWatchRequest.ActionExecutionMode.SIMULATE);
        request.setRecordExecution(true);
        request.setIgnoreCondition(true);
        request.setTriggerData("{\"triggered_time\":\"now\"}");
        request.setDebug(true);
        ExecuteWatchResponse response = client.watcher().executeWatch(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation("观察者状态")
    @PutMapping("/stats")
    public WatcherStatsResponse stats()throws Exception{
        WatcherStatsRequest request = new WatcherStatsRequest(true, true);
        WatcherStatsResponse response = client.watcher().watcherStats(request, RequestOptions.DEFAULT);
        return response;
    }


}

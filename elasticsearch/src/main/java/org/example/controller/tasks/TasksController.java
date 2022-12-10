package org.example.controller.tasks;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.admin.cluster.node.tasks.list.ListTasksRequest;
import org.elasticsearch.action.admin.cluster.node.tasks.list.ListTasksResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.tasks.CancelTasksRequest;
import org.elasticsearch.client.tasks.CancelTasksResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.tasks.TaskId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author: zyh
 * @date: 2022/12/10
 */
@Api(tags = "任务")
@RestController
@RequestMapping("/tasks")
public class TasksController {
    @Autowired
    private RestHighLevelClient client;


    @ApiOperation("创建")
    @PutMapping()
    public ListTasksResponse create (@RequestParam String repositoryName) throws Exception{
        ListTasksRequest request = new ListTasksRequest();
//        request.setActions("cluster:*");
//        request.setNodes("nodeId1", "nodeId2");
//        request.setParentTaskId(new TaskId("parentTaskId", 42));

        request.setDetailed(true);
        request.setWaitForCompletion(true);
        request.setTimeout(TimeValue.timeValueSeconds(50));
        request.setTimeout("50s");
        ListTasksResponse response = client.tasks().list(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation("取消")
    @GetMapping("cancel")
    public CancelTasksResponse cancel (@RequestParam String repositoryName) throws Exception{
        CancelTasksRequest request = new org.elasticsearch.client.tasks.CancelTasksRequest.Builder()
                .withNodesFiltered(Arrays.asList("nodeId1", "nodeId2"))
                .withActionsFiltered(Collections.singletonList("cluster:*"))
                .build();
        CancelTasksResponse response = client.tasks().cancel(request, RequestOptions.DEFAULT);
        return response;
    }
}

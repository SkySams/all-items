package org.example.controller.ccr;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.support.ActiveShardCount;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.ccr.*;
import org.elasticsearch.client.core.AcknowledgedResponse;
import org.elasticsearch.client.core.BroadcastResponse;
import org.elasticsearch.common.settings.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: zyh
 * @date: 2022/12/12
 */
@Api(tags = "CCR模块")
@RestController
@RequestMapping("/ccr")
public class CcrController {

    @Autowired
    private RestHighLevelClient client;

    @ApiOperation(value = "创建")
    @PostMapping("create")
    public PutFollowResponse create() throws Exception{
        PutFollowRequest putFollowRequest = new PutFollowRequest(
                "local",
                "leader",
                "follower",
                ActiveShardCount.ONE
        );
        Settings settings = Settings.builder().put("index.number_of_replicas", 0L).build();
        putFollowRequest.setSettings(settings);

        PutFollowResponse response = client.ccr().putFollow(putFollowRequest, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "暂停")
    @PutMapping("pause")
    public AcknowledgedResponse pause(@RequestParam String followIndex) throws Exception{
        PauseFollowRequest request = new PauseFollowRequest(followIndex);
        AcknowledgedResponse response = client.ccr().pauseFollow(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "查看")
    @PutMapping("resume")
    public AcknowledgedResponse resume(@RequestParam String followIndex) throws Exception{
        ResumeFollowRequest request = new ResumeFollowRequest(followIndex);
        AcknowledgedResponse response = client.ccr().resumeFollow(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "取消")
    @PutMapping("unfollow")
    public AcknowledgedResponse unfollow(@RequestParam String followIndex) throws Exception{
        UnfollowRequest request = new UnfollowRequest(followIndex);
        AcknowledgedResponse response = client.ccr().unfollow(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "清楚")
    @PutMapping("forget")
    public BroadcastResponse forget() throws Exception{
        final ForgetFollowerRequest request = new ForgetFollowerRequest("", "", "", "", "");
        final BroadcastResponse response = client
                .ccr()
                .forgetFollower(request, RequestOptions.DEFAULT);
        return response;
    }




}

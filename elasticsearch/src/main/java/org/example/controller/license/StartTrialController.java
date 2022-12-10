package org.example.controller.license;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.license.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author: zyh
 * @date: 2022/12/10
 */
@Api(tags = "START-TRIAL")
@RestController
@RequestMapping("/trial")
public class StartTrialController {

    @Autowired
    private RestHighLevelClient client;

    @ApiOperation(value = "创建")
    @PostMapping()
    public StartTrialResponse createTrial() throws Exception {
        StartTrialRequest request = new StartTrialRequest(true);
        StartTrialResponse response = client.license().startTrial(request, RequestOptions.DEFAULT);

        boolean acknowledged = response.isAcknowledged();
        boolean trialWasStarted = response.isTrialWasStarted();
        String licenseType = response.getLicenseType();
        String errorMessage = response.getErrorMessage();
        String acknowledgeHeader = response.getAcknowledgeHeader();
        Map<String, String[]> acknowledgeMessages = response.getAcknowledgeMessages();

        return response;
    }

    @ApiOperation(value = "获取")
    @GetMapping()
    public GetTrialStatusResponse getTrial() throws Exception {
        GetTrialStatusResponse response = client.license().getTrialStatus(RequestOptions.DEFAULT);
        boolean eligibleToStartTrial = response.isEligibleToStartTrial();
        return response;
    }

    @ApiOperation(value = "获取基础status")
    @GetMapping("/basic/status")
    public GetBasicStatusResponse getBasicStatus() throws Exception {
        GetBasicStatusResponse response = client.license().getBasicStatus(RequestOptions.DEFAULT);
        boolean eligibleToStartbasic = response.isEligibleToStartBasic();
        return response;
    }
}

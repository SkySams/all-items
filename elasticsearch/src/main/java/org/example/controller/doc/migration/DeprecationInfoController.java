package org.example.controller.doc.migration;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.license.StartBasicResponse;
import org.elasticsearch.client.migration.DeprecationInfoRequest;
import org.elasticsearch.client.migration.DeprecationInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: zyh
 * @date: 2022/12/10
 */
@Api(tags = "迁移")
@RestController
@RequestMapping("/deprecation")
public class DeprecationInfoController {

    @Autowired
    private RestHighLevelClient client;


    @ApiOperation(value = "获取")
    @GetMapping()
    public DeprecationInfoResponse getDeprecation() throws Exception {
        List<String> indices = new ArrayList<>();
        indices.add("test");
        DeprecationInfoRequest deprecationInfoRequest = new DeprecationInfoRequest(indices);
        DeprecationInfoResponse deprecationInfoResponse = client.migration().getDeprecationInfo(deprecationInfoRequest, RequestOptions.DEFAULT);
        List<DeprecationInfoResponse.DeprecationIssue> clusterIssues = deprecationInfoResponse.getClusterSettingsIssues();
        List<DeprecationInfoResponse.DeprecationIssue> nodeIssues = deprecationInfoResponse.getNodeSettingsIssues();
        Map<String, List<DeprecationInfoResponse.DeprecationIssue>> indexIssues = deprecationInfoResponse.getIndexSettingsIssues();
        List<DeprecationInfoResponse.DeprecationIssue> mlIssues = deprecationInfoResponse.getMlSettingsIssues();
        return deprecationInfoResponse;

    }

}

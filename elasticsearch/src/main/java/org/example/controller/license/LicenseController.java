package org.example.controller.license;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.admin.cluster.storedscripts.GetStoredScriptResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.license.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author: zyh
 * @date: 2022/12/10
 */
@Api(tags = "LICENSE")
@RestController
@RequestMapping("/license")
public class LicenseController {

    @Autowired
    private RestHighLevelClient client;

    /**
     * This API creates and enables a basic license using the startBasic() method.
     */
    @ApiOperation(value = "创建")
    @PostMapping()
    public StartBasicResponse createLicense() throws Exception {
        StartBasicRequest request = new StartBasicRequest();
        StartBasicResponse response = client.license().startBasic(request, RequestOptions.DEFAULT);

        boolean acknowledged = response.isAcknowledged();
        boolean basicStarted = response.isBasicStarted();
        String errorMessage = response.getErrorMessage();
        String acknowledgeMessage = response.getAcknowledgeMessage();
        Map<String, String[]> acknowledgeMessages = response.getAcknowledgeMessages();

        return response;
    }

    @ApiOperation(value = "获取")
    @GetMapping()
    public GetLicenseResponse getLicence() throws Exception {
        GetLicenseRequest request = new GetLicenseRequest();
        GetLicenseResponse response = client.license().getLicense(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "删除")
    @DeleteMapping()
    public AcknowledgedResponse deleted() throws Exception {
        DeleteLicenseRequest request = new DeleteLicenseRequest();
        AcknowledgedResponse response = client.license().deleteLicense(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public PutLicenseResponse updated() throws Exception {
        PutLicenseRequest request = new PutLicenseRequest();
        request.setLicenseDefinition("licenseDefinition");
        request.setAcknowledge(false);
        PutLicenseResponse response = client.license().putLicense(request, RequestOptions.DEFAULT);
        return response;
    }
}

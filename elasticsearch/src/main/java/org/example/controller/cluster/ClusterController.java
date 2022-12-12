package org.example.controller.cluster;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.action.admin.cluster.settings.ClusterGetSettingsRequest;
import org.elasticsearch.action.admin.cluster.settings.ClusterGetSettingsResponse;
import org.elasticsearch.action.admin.cluster.settings.ClusterUpdateSettingsRequest;
import org.elasticsearch.action.admin.cluster.settings.ClusterUpdateSettingsResponse;
import org.elasticsearch.action.support.ActiveShardCount;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.cluster.RemoteInfoRequest;
import org.elasticsearch.client.cluster.RemoteInfoResponse;
import org.elasticsearch.client.indices.DeleteComposableIndexTemplateRequest;
import org.elasticsearch.client.indices.GetComponentTemplatesRequest;
import org.elasticsearch.client.indices.GetComponentTemplatesResponse;
import org.elasticsearch.client.indices.PutComponentTemplateRequest;
import org.elasticsearch.cluster.health.ClusterHealthStatus;
import org.elasticsearch.cluster.metadata.AliasMetadata;
import org.elasticsearch.cluster.metadata.ComponentTemplate;
import org.elasticsearch.cluster.metadata.Template;
import org.elasticsearch.cluster.routing.allocation.decider.EnableAllocationDecider;
import org.elasticsearch.common.compress.CompressedXContent;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.indices.recovery.RecoverySettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: zyh
 * @date: 2022/12/10
 */
@Api(tags = "集群模块")
@RestController
@RequestMapping("/cluster")
public class ClusterController {

    @Autowired
    private RestHighLevelClient client;

    @ApiOperation("设置集群")
    @PutMapping("")
    public ClusterUpdateSettingsResponse settingsCluster () throws Exception{
        ClusterUpdateSettingsRequest request = new ClusterUpdateSettingsRequest();

        // Creates a transient setting as Settings
        String transientSettingKey = RecoverySettings.INDICES_RECOVERY_MAX_BYTES_PER_SEC_SETTING.getKey();
        int transientSettingValue = 10;
        Settings transientSettings = Settings.builder().put(transientSettingKey, transientSettingValue, ByteSizeUnit.BYTES).build();

        //Creates a persistent setting as Settings
        String persistentSettingKey = EnableAllocationDecider.CLUSTER_ROUTING_ALLOCATION_ENABLE_SETTING.getKey();
        String persistentSettingValue = EnableAllocationDecider.Allocation.NONE.name();
        Settings persistentSettings = Settings.builder().put(persistentSettingKey, persistentSettingValue).build();

        request.transientSettings(transientSettings);
        ClusterUpdateSettingsResponse response = client.cluster().putSettings(request, RequestOptions.DEFAULT);

        boolean acknowledged = response.isAcknowledged();
        Settings transientSettingsResponse = response.getTransientSettings();
        Settings persistentSettingsResponse = response.getPersistentSettings();
        System.out.println(acknowledged);
        System.out.println(transientSettingsResponse);
        System.out.println(persistentSettingsResponse);

        return  response;
    }

    @ApiOperation("获取集群")
    @GetMapping()
    public ClusterGetSettingsResponse getCluster () throws Exception{
        ClusterGetSettingsRequest request = new ClusterGetSettingsRequest();
        request.includeDefaults(true);
        request.local(true);
        request.masterNodeTimeout(TimeValue.timeValueMinutes(1));
        ClusterGetSettingsResponse response = client.cluster().getSettings(request, RequestOptions.DEFAULT);

        Settings persistentSettings = response.getPersistentSettings();
        Settings transientSettings = response.getTransientSettings();
        Settings defaultSettings = response.getDefaultSettings();
        String settingValue = response.getSetting("cluster.routing.allocation.enable");
        System.out.println(persistentSettings);
        System.out.println(transientSettings);
        System.out.println(defaultSettings);
        System.out.println(settingValue);
        return response;
    }

    @ApiOperation("集群运行状况API")
    @GetMapping("/health/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index",dataTypeClass = String.class)
    public ClusterHealthResponse getClusterHealth (@PathVariable("index") String index) throws Exception{
        ClusterHealthRequest request = new ClusterHealthRequest(index);
        request.timeout(TimeValue.timeValueSeconds(50));
        request.masterNodeTimeout(TimeValue.timeValueSeconds(20));
        request.waitForStatus(ClusterHealthStatus.YELLOW);
        request.waitForActiveShards(ActiveShardCount.ALL);
        ClusterHealthResponse response = client.cluster().health(request, RequestOptions.DEFAULT);
        int activeShards = response.getActiveShards();
        int activePrimaryShards = response.getActivePrimaryShards();
        int relocatingShards = response.getRelocatingShards();
        int initializingShards = response.getInitializingShards();
        int unassignedShards = response.getUnassignedShards();
        int delayedUnassignedShards = response.getDelayedUnassignedShards();
        double activeShardsPercent = response.getActiveShardsPercent();
        return response;
    }

    @ApiOperation("远程集群信息")
    @GetMapping("/remote")
    public RemoteInfoResponse remoteClusterInfo () throws Exception{
        RemoteInfoRequest request = new RemoteInfoRequest();
        RemoteInfoResponse response = client.cluster().remoteInfo(request,RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation("获取组件模板")
    @GetMapping("/component/template/{name}")
    @ApiImplicitParam(value = "名称", name = "name",dataTypeClass = String.class)
    public GetComponentTemplatesResponse getComponentTemplate (@PathVariable String name) throws Exception{
        GetComponentTemplatesRequest request = new GetComponentTemplatesRequest(name);
        request.setMasterNodeTimeout(TimeValue.timeValueMinutes(1));
        GetComponentTemplatesResponse response = client.cluster().getComponentTemplate(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation("更新组件模块")
    @PutMapping("/component/template/{name}")
    @ApiImplicitParam(value = "名称", name = "name",dataTypeClass = String.class)
    public AcknowledgedResponse updateComponentTemplate(@PathVariable String name) throws Exception{
        PutComponentTemplateRequest request = new PutComponentTemplateRequest().name(name);
        Settings settings = Settings.builder()
                .put("index.number_of_shards", 3)
                .put("index.number_of_replicas", 1)
                .build();
        String mappingJson = "{\n" +
                "  \"properties\": {\n" +
                "    \"message\": {\n" +
                "      \"type\": \"text\"\n" +
                "    }\n" +
                "  }\n" +
                "}";
        AliasMetadata twitterAlias = AliasMetadata.builder("twitter_alias").build();
        Map<String, AliasMetadata> aliases = new HashMap<>();
        aliases.put("twitter_alias", twitterAlias);
        Template template = new Template(settings, new CompressedXContent(mappingJson), aliases);

        request.componentTemplate(new ComponentTemplate(template, null, null));
        AcknowledgedResponse response = client.cluster().putComponentTemplate(request, RequestOptions.DEFAULT);
        return response;
    }

    private void version(String name) throws Exception{
        PutComponentTemplateRequest request = new PutComponentTemplateRequest().name(name);
        Settings settings = Settings.builder()
                .put("index.number_of_replicas", 3)
                .build();
        Template template = new Template(settings, null, null);

        request.componentTemplate(new ComponentTemplate(template, 3L, null));
        client.cluster().putComponentTemplate(request, RequestOptions.DEFAULT);
    }

    @ApiOperation("删除")
    @DeleteMapping ("/{name}")
    @ApiImplicitParam(value = "名称", name = "name",dataTypeClass = String.class)
    public AcknowledgedResponse deleted(@PathVariable String name) throws Exception{
        DeleteComposableIndexTemplateRequest request = new DeleteComposableIndexTemplateRequest(name);
        AcknowledgedResponse response = client.indices().deleteIndexTemplate(request, RequestOptions.DEFAULT);
        return response;
    }



}

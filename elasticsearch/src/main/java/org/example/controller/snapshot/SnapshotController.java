package org.example.controller.snapshot;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.admin.cluster.repositories.delete.DeleteRepositoryRequest;
import org.elasticsearch.action.admin.cluster.repositories.get.GetRepositoriesRequest;
import org.elasticsearch.action.admin.cluster.repositories.get.GetRepositoriesResponse;
import org.elasticsearch.action.admin.cluster.repositories.put.PutRepositoryRequest;
import org.elasticsearch.action.admin.cluster.repositories.verify.VerifyRepositoryRequest;
import org.elasticsearch.action.admin.cluster.repositories.verify.VerifyRepositoryResponse;
import org.elasticsearch.action.admin.cluster.settings.ClusterUpdateSettingsResponse;
import org.elasticsearch.action.admin.cluster.snapshots.clone.CloneSnapshotRequest;
import org.elasticsearch.action.admin.cluster.snapshots.create.CreateSnapshotRequest;
import org.elasticsearch.action.admin.cluster.snapshots.create.CreateSnapshotResponse;
import org.elasticsearch.action.admin.cluster.snapshots.delete.DeleteSnapshotRequest;
import org.elasticsearch.action.admin.cluster.snapshots.get.GetSnapshotsRequest;
import org.elasticsearch.action.admin.cluster.snapshots.get.GetSnapshotsResponse;
import org.elasticsearch.action.admin.cluster.snapshots.restore.RestoreSnapshotRequest;
import org.elasticsearch.action.admin.cluster.snapshots.restore.RestoreSnapshotResponse;
import org.elasticsearch.action.admin.cluster.snapshots.status.SnapshotsStatusRequest;
import org.elasticsearch.action.admin.cluster.snapshots.status.SnapshotsStatusResponse;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.repositories.fs.FsRepository;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.snapshots.SnapshotId;
import org.elasticsearch.snapshots.SnapshotInfo;
import org.elasticsearch.snapshots.SnapshotShardFailure;
import org.elasticsearch.snapshots.SnapshotState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.EnumSet;
import java.util.List;

/**
 * @author: zyh
 * @date: 2022/12/10
 */
@Api(tags = "快照")
@RestController
@RequestMapping("/snapshot")
public class SnapshotController {

    @Autowired
    private RestHighLevelClient client;


    @ApiOperation("创建")
    @PutMapping("/repository")
    public AcknowledgedResponse create (@RequestParam String repositoryName) throws Exception{
        PutRepositoryRequest request = new PutRepositoryRequest();
        String locationKey = FsRepository.LOCATION_SETTING.getKey();
        String locationValue = ".";
        String compressKey = FsRepository.COMPRESS_SETTING.getKey();
        boolean compressValue = true;

        Settings settings = Settings.builder()
                .put(locationKey, locationValue)
                .put(compressKey, compressValue)
                .build();

        request.settings(settings);

        request.name(repositoryName);

//        Settings.Builder settingsBuilder = Settings.builder()
//                .put(locationKey, locationValue)
//                .put(compressKey, compressValue);
//        request.settings(settingsBuilder);

//        Map<String, Object> map = new HashMap<>();
//        map.put(locationKey, locationValue);
//        map.put(compressKey, compressValue);
//        request.settings(map);

        request.type(FsRepository.TYPE);
        AcknowledgedResponse response = client.snapshot().createRepository(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation("获取")
    @GetMapping("/repository")
    public GetRepositoriesResponse get(@RequestParam String repositoryName) throws Exception{
        GetRepositoriesRequest request = new GetRepositoriesRequest();
        String [] repositories = new String[] {repositoryName};
        request.repositories(repositories);
        GetRepositoriesResponse response = client.snapshot().getRepository(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation("删除")
    @DeleteMapping("/repository")
    public AcknowledgedResponse deleted(@RequestParam String repositoryName) throws Exception{
        DeleteRepositoryRequest request = new DeleteRepositoryRequest(repositoryName);
        request.timeout(TimeValue.timeValueMinutes(1));
        request.masterNodeTimeout(TimeValue.timeValueMinutes(1));
        AcknowledgedResponse response = client.snapshot().deleteRepository(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation("验证")
    @GetMapping("/repository/verify")
    public VerifyRepositoryResponse verify(@RequestParam String repositoryName) throws Exception{
        VerifyRepositoryRequest request = new VerifyRepositoryRequest(repositoryName);
        request.timeout(TimeValue.timeValueMinutes(1));
        request.masterNodeTimeout(TimeValue.timeValueMinutes(1));
        VerifyRepositoryResponse response = client.snapshot().verifyRepository(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation("创建snapshot")
    @PostMapping()
    public CreateSnapshotResponse createSnapshot (@RequestParam String repositoryName) throws Exception{
        CreateSnapshotRequest request = new CreateSnapshotRequest();
        request.repository(repositoryName);
        CreateSnapshotResponse response = client.snapshot().create(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation("获取snapshot")
    @GetMapping()
    public GetSnapshotsResponse getSnapshot(@RequestParam String repositoryName) throws Exception{
        GetSnapshotsRequest request = new GetSnapshotsRequest();
        GetSnapshotsResponse response = client.snapshot().get(request, RequestOptions.DEFAULT);

        List<SnapshotInfo> snapshotsInfos = response.getSnapshots();
        SnapshotInfo snapshotInfo = snapshotsInfos.get(0);
        RestStatus restStatus = snapshotInfo.status();
        SnapshotId snapshotId = snapshotInfo.snapshotId();
        SnapshotState snapshotState = snapshotInfo.state();
        List<SnapshotShardFailure> snapshotShardFailures = snapshotInfo.shardFailures();
        long startTime = snapshotInfo.startTime();
        long endTime = snapshotInfo.endTime();

        return response;
    }

    @ApiOperation("获取snapshot状态")
    @GetMapping("/status")
    public SnapshotsStatusResponse getSnapshotStatus(@RequestParam String repositoryName) throws Exception{
        SnapshotsStatusRequest request = new SnapshotsStatusRequest(repositoryName);
        SnapshotsStatusResponse response = client.snapshot().status(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation("删除sanapshot")
    @DeleteMapping ()
    public AcknowledgedResponse deletedSanapshot(@RequestParam String repositoryName) throws Exception{
        DeleteSnapshotRequest request = new DeleteSnapshotRequest(repositoryName);
        AcknowledgedResponse response = client.snapshot().delete(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation("恢复Sanapshot/Restore")
    @GetMapping ("/restore")
    public RestoreSnapshotResponse restore(@RequestParam String repositoryName) throws Exception{
        RestoreSnapshotRequest request = new RestoreSnapshotRequest(repositoryName,"");
        request.indexSettings(
                Settings.builder()
                        .put("index.number_of_replicas", 0)
                        .build());

        request.ignoreIndexSettings("index.refresh_interval", "index.search.idle.after");
        request.indicesOptions(new IndicesOptions(
                EnumSet.of(IndicesOptions.Option.IGNORE_UNAVAILABLE),
                EnumSet.of(IndicesOptions.WildcardStates.OPEN)));

        RestoreSnapshotResponse response = client.snapshot().restore(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation("关闭Sanapshot")
    @GetMapping ("/clost")
    public AcknowledgedResponse clost(@RequestParam String repositoryName) throws Exception{
//        CloneSnapshotRequest request = new CloneSnapshotRequest(repositoryName);
        AcknowledgedResponse response = client.snapshot().clone(null, RequestOptions.DEFAULT);
        return response;
    }


}

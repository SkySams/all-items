package org.example.controller.doc.source;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.VersionType;
import org.elasticsearch.rest.RestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zyh
 * @date: 2022/12/6
 */
@Api(tags = "DELETED")
@RestController
@RequestMapping("/deleted")
public class DeletedController {

    @Autowired
    private RestHighLevelClient client;

    @ApiOperation(value = "删除", notes = "")
    @PutMapping("/{index}/{id}")
    @ApiImplicitParam(value = "索引名称", name = "index")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "索引", name = "index"),
            @ApiImplicitParam(value = "文本id", name = "id")
    })
    public DeleteResponse deleted(@PathVariable String index, @PathVariable("id") String id) throws Exception {
        DeleteRequest request = new DeleteRequest(index,id);
        request.routing("routing");
        request.timeout(TimeValue.timeValueMinutes(2));
        request.timeout("2m");
        request.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
        request.setRefreshPolicy("wait_for");
        request.version(2);
        request.versionType(VersionType.EXTERNAL);
        DeleteResponse deleteResponse = client.delete(request, RequestOptions.DEFAULT);

        String responseIndex = deleteResponse.getIndex();
        String responseId = deleteResponse.getId();
        long version = deleteResponse.getVersion();
        ReplicationResponse.ShardInfo shardInfo = deleteResponse.getShardInfo();
        if (shardInfo.getTotal() != shardInfo.getSuccessful()) {

        }
        if (shardInfo.getFailed() > 0) {
            for (ReplicationResponse.ShardInfo.Failure failure :
                    shardInfo.getFailures()) {
                String reason = failure.reason();
            }
        }

        return deleteResponse;
    }

    @ApiOperation(value = "删除2", notes = "")
    @PutMapping("/type2/{index}/{id}")
    @ApiImplicitParam(value = "索引名称", name = "index")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "索引", name = "index"),
            @ApiImplicitParam(value = "文本id", name = "id")
    })
    public DeleteResponse deletedType2(@PathVariable String index, @PathVariable("id") String id) throws Exception {
        DeleteResponse deleteResponse = null;
        try {
            deleteResponse = client.delete(
                    new DeleteRequest(index, id).setIfSeqNo(100).setIfPrimaryTerm(2),
                    RequestOptions.DEFAULT);
        } catch (ElasticsearchException exception) {
            if (exception.status() == RestStatus.CONFLICT) {

            }
        }
        return deleteResponse;
    }

}

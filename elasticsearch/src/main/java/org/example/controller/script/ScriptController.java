package org.example.controller.script;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.admin.cluster.storedscripts.DeleteStoredScriptRequest;
import org.elasticsearch.action.admin.cluster.storedscripts.GetStoredScriptRequest;
import org.elasticsearch.action.admin.cluster.storedscripts.GetStoredScriptResponse;
import org.elasticsearch.action.admin.cluster.storedscripts.PutStoredScriptRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.common.bytes.BytesArray;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: zyh
 * @date: 2022/12/10
 */
@Api(tags = "脚本API")
@RestController
@RequestMapping("/script")
public class ScriptController {

    @Autowired
    private RestHighLevelClient client;

    @ApiOperation(value = "获取")
    @GetMapping ()
    public GetStoredScriptResponse getScript( @RequestParam String id) throws Exception {
        GetStoredScriptRequest request = new GetStoredScriptRequest(id);
        request.masterNodeTimeout(TimeValue.timeValueSeconds(50));
        GetStoredScriptResponse response = client.getScript(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "创建")
    @PostMapping ()
    public AcknowledgedResponse createScript( @RequestParam String id) throws Exception {
        PutStoredScriptRequest request = new PutStoredScriptRequest();
        request.id(id);
        request.content(new BytesArray(
                "{\n" +
                        "\"script\": {\n" +
                        "\"lang\": \"painless\",\n" +
                        "\"source\": \"Math.log(_score * 2) + params.multiplier\"" +
                        "}\n" +
                        "}\n"
        ), XContentType.JSON);
        AcknowledgedResponse putStoredScriptResponse = client.putScript(request, RequestOptions.DEFAULT);

//        XContentBuilder builder = XContentFactory.jsonBuilder();
//        builder.startObject();
//        {
//            builder.startObject("script");
//            {
//                builder.field("lang", "painless");
//                builder.field("source", "Math.log(_score * 2) + params.multiplier");
//            }
//            builder.endObject();
//        }
//        builder.endObject();
//        request.content(BytesReference.bytes(builder), XContentType.JSON);

        return putStoredScriptResponse;
    }

    @ApiOperation(value = "删除")
    @DeleteMapping ()
    public AcknowledgedResponse deleted( @RequestParam String id) throws Exception {
        DeleteStoredScriptRequest deleteRequest = new DeleteStoredScriptRequest(id);
        AcknowledgedResponse response = client.deleteScript(deleteRequest, RequestOptions.DEFAULT);
        return response;
    }

}

package org.example.controller.doc;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.example.entity.es.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author: zyh
 * @date: 2022/12/2
 */
@Api(tags = "DOX")
@RestController
@RequestMapping("/dox")
public class StudentController {

    @Autowired
    private RestHighLevelClient client;

    @ApiOperation(value = "创建数据",notes = "创建数据，添加到文档中")
    @PostMapping("/{index}/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "索引名称", name = "index"),
            @ApiImplicitParam(value = "唯一性标识", name = "id")
    })
    public IndexResponse createDoc(@PathVariable("index") String index,@PathVariable("id") String id,@RequestBody Student student) throws Exception {
        IndexRequest request = new IndexRequest();
        request.index(index).id(id);
        ObjectMapper objectMapper = new ObjectMapper();
        // 设置索引及唯一性标识
        String productJson = objectMapper.writeValueAsString(student);
        // 添加文档数据，数据格式为 JSON 格式
        request.source(productJson, XContentType.JSON);
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        System.out.println("_index:" + response.getIndex());
        System.out.println("_id:" + response.getId());
        System.out.println("_result:" + response.getResult());
        return response;
    }

    @ApiOperation("修改文档")
    @PutMapping ("/{index}/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "索引名称", name = "index"),
            @ApiImplicitParam(value = "唯一性标识", name = "id")
    })
    public UpdateResponse updateDoc(@PathVariable("index") String index,@PathVariable("id") String id,@RequestBody Student student) throws Exception {
        UpdateRequest request = new UpdateRequest();
        // 配置修改参数
        request.index(index).id(id);
        // 设置请求体，对数据进行修改
        request.doc(XContentType.JSON, "sex", "女");
        // 客户端发送请求，获取响应对象
        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
        System.out.println("_index:" + response.getIndex());
        System.out.println("_id:" + response.getId());
        System.out.println("_result:" + response.getResult());
        return response;
    }

    @ApiOperation("查看文档")
    @GetMapping ("/{index}/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "索引名称", name = "index"),
            @ApiImplicitParam(value = "唯一性标识", name = "id")
    })
    public GetResponse getDoc(@PathVariable("index") String index,@PathVariable("id") String id) throws Exception {
        //1.创建请求对象
        GetRequest request = new GetRequest().index(index).id(id);
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation("删除文档")
    @DeleteMapping ("/{index}/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "索引名称", name = "index"),
            @ApiImplicitParam(value = "唯一性标识", name = "id")
    })
    public DeleteResponse deletedDoc(@PathVariable("index") String index,@PathVariable("id") String id) throws Exception {
        //1.创建请求对象
        DeleteRequest request = new DeleteRequest().index(index).id(id);
        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
        return response;
    }

}

package org.example.controller.structure;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.textstructure.FindStructureRequest;
import org.elasticsearch.client.textstructure.FindStructureResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zyh
 * @date: 2022/12/12
 */
@Api(tags = "FIND-STRUCTURE")
@RestController
@RequestMapping("/find")
public class FindStructureController {

    @Autowired
    private RestHighLevelClient client;

    @ApiOperation("查找结构")
    @GetMapping("")
    public FindStructureResponse findStructure() throws Exception {
        FindStructureRequest request = new FindStructureRequest();
//        request.setSample(Files.readAllBytes(anInterestingFile));
        FindStructureResponse response = client.textStructure().findStructure(
                request,
                RequestOptions.DEFAULT
        );
        return response;
    }


}

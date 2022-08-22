package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.service.GoodsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: zyh
 * @date: 2022/8/22
 */
@Api(tags = "ZK")
@RestController
public class BaseNiceController {

    @Resource
    private GoodsService goodsService;


    @ApiOperation("OPRATION")
    @GetMapping("opration")
    public String opration(){
        return "success";
    }


    @ApiOperation("MAP")
    @GetMapping("MAP")
    public  List<Map<String,Object>> MAP(){
        return goodsService.de();
    }

}

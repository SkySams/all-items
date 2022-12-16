package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.esdao.ProductRepository;
import org.example.entity.es.ProductEs;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: zyh
 * @date: 2022/4/8
 */
@Api(tags = "产品")
@RestController
@RequestMapping("product")
public class ProductController {

    @Resource
    private ProductRepository productRepository;

    @ApiOperation("同步ES")
    @PostMapping("sync")
    public String sync(@RequestBody ProductEs productEs){
        productRepository.save(productEs);
        return "success";
    }


    @ApiOperation("搜查名称")
    @GetMapping("seach/{name}")
    public List<ProductEs> seachName(@PathVariable String name){
        return productRepository.findByName(name);
    }

}

package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.Esdao.ProductRepository;
import org.example.entity.Product;
import org.example.service.ProductService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: zyh
 * @date: 2022/4/18
 */
@Api(tags = "HTTP")
@RestController
public class HttpEntityController {

    @Resource
    private ProductService productService;

    @ApiOperation("列表")
    @GetMapping("list")
    public ResponseEntity<List<Product>> list() {
        List<Product> productList = productService.list();
        return new ResponseEntity(productList, HttpStatus.OK);
    }

}

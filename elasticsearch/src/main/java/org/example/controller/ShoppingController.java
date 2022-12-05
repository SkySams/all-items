package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.example.Esdao.ShoppingRepository;
import org.example.entity.Product;
import org.example.entity.es.ShoppingEs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

/**
 * @author: zyh
 * @date: 2022/12/5
 */
@Api(tags = "购物")
@RestController
@RequestMapping("shopping")
public class ShoppingController {

    /**
     * 启动时，会自动创建
     */
    @Autowired
    private ShoppingRepository shoppingRepository;


    @ApiOperation("删除索引")
    @DeleteMapping
    public String deleteIndex() {
        //创建索引，系统初始化会自动创建索引
        return "";
    }

    @ApiOperation("新增数据")
    @PostMapping
    public ShoppingEs save(@RequestBody ShoppingEs shoppingEs) {
        ShoppingEs es = shoppingRepository.save(shoppingEs);
        return es;
    }

    @ApiOperation("更新数据")
    @PutMapping
    public ShoppingEs update(@RequestBody ShoppingEs shoppingEs) {
        ShoppingEs es = shoppingRepository.save(shoppingEs);
        return es;
    }

    @ApiOperation("根据id查询")
    @PutMapping("/{id}")
    public ShoppingEs findById(@PathVariable("id") long id) {
        ShoppingEs shopping = shoppingRepository.findById(id).get();
        return shopping;
    }

    @ApiOperation("查询所有")
    @PutMapping("/find/all")
    public Iterable findAll() {
        Iterable<ShoppingEs> products = shoppingRepository.findAll();
        return products;
    }

    @ApiOperation("根据id删除")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        ShoppingEs product = new ShoppingEs();
        product.setId(id);
        shoppingRepository.delete(product);
    }

    @ApiOperation("分页查询")
    @GetMapping("page/{current}/{size}")
    public Page<ShoppingEs> findByPageable(@PathVariable Integer current, @PathVariable Integer size) {
        // 排序
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        //设置查询分页
        PageRequest pageRequest = PageRequest.of(current, size, sort);
        //分页查询
        Page<ShoppingEs> productPage = shoppingRepository.findAll(pageRequest);
        return productPage;
    }


}

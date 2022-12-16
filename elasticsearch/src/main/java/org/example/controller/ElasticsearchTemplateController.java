package org.example.controller;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.example.esdao.EmployeeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: zyh
 * @date: 2022/4/8
 */
@RestController
public class ElasticsearchTemplateController {

    @Resource
    private EmployeeRepository employeeRepository;

    @GetMapping("search")
    public void search(){
//        employeeRepository.searchSimilar()
        /**
         * 创建查询体
         */
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        /**
         * 设置聚合条件
         */
        RangeQueryBuilder query = QueryBuilders.rangeQuery("age").from("30").to("60");

        /**
         * 将聚合条件设置入查询体之中
         */
        builder.must(query);


    }

}

package org.example.controller.springboot;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.Esdao.ElasticsearchDBRepository;
import org.example.entity.es.ElasticsearchDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.web.bind.annotation.*;

/**
 * @author: zyh
 * @date: 2022/12/13
 */
@Api(tags = "SPRING-BOOT")
@RestController
public class ElasticsearchDBController {

    @Autowired
    private ElasticsearchDBRepository elasticsearchDBRepository;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;


    @ApiOperation("获取")
    @GetMapping("/elastic/search/{id}")
    public ElasticsearchDB findById(@PathVariable("id")  Long id) {
        ElasticsearchDB db = elasticsearchOperations.get(id.toString(), ElasticsearchDB.class);
        return db;
    }

    @ApiOperation("创建")
    @PostMapping("/elastic")
    public ElasticsearchDB save(@RequestBody ElasticsearchDB db) {
        ElasticsearchDB savedEntity = elasticsearchOperations.save(db);
        return savedEntity;
    }


    @ApiOperation("统计")
    @GetMapping("/count")
    public long count(@RequestParam String name) {
       long count = elasticsearchDBRepository.countByName(name);
        return count;
    }


    @ApiOperation("全部数据")
    @GetMapping("/findAll")
    public  Iterable<ElasticsearchDB> findAll() {
        Iterable<ElasticsearchDB> elasticsearchDBStream = elasticsearchDBRepository.findAll();
        return  elasticsearchDBStream;
    }





}

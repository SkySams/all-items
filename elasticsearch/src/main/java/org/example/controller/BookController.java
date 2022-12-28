package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.entity.es.BookEs;
import org.example.esdao.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: zyh
 * @date: 2022/12/27
 */
@Api(tags = "BOOKS")
@RestController
@RequestMapping("/books")
public class BookController {

    @Resource
    private BookRepository bookRepository;

    @ApiOperation("查询")
    @GetMapping("/query")
    public ResponseEntity one(@RequestParam String name, @RequestParam Integer price){
        List<BookEs> bookEs = bookRepository.findByNameAndPrice(name,price);
        return ResponseEntity.ok(bookEs);
    }


    @ApiOperation("新增数据")
    @PostMapping("/data")
    public ResponseEntity data(@RequestBody BookEs bookEs){
        bookRepository.save(bookEs);
        return ResponseEntity.ok("success");
    }

    @ApiOperation("分页")
    @PostMapping("/page")
    public ResponseEntity findBy(@RequestParam String name){
        Pageable pageable = PageRequest.of(0,10);
        Page<BookEs> page = bookRepository.findByName(name,pageable);
        return ResponseEntity.ok(page);
    }


}

package org.example.controller;

import io.swagger.annotations.Api;
import org.example.esdao.BookRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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





}

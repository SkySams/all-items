package com.example.basicspring.controller;

import com.example.basicspring.entity.dto.Cat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

/**
 * @author: zyh
 * @date: 2023/2/6
 */
@RestController
public class OptionalController {

    @GetMapping("/getMyParam")
    public void test(@RequestParam Map paramMap) {

//        String name = null;
//        if (null != paramMap.get("name")) {
//            name = (String) paramMap.get("name");
//        }
//
//        String classRoom = null;
//        if (null != paramMap.get("classRoom")) {
//            name = (String) paramMap.get("classRoom");
//        }
//        String nickName = null;
//        if (null != paramMap.get("nickName")) {
//            nickName = (String) paramMap.get("nickName");
//        }
//        Integer age = 0;
//        if (null != paramMap.get("age")) {
//            age = Integer.valueOf((String) paramMap.get("age"));
//        }
        Object  name= Optional.ofNullable(paramMap.get("name")).orElse("NEW-JC");
        Object age = Optional.ofNullable(paramMap.get("age")).orElse(0);

    }

    @GetMapping("/testNullParamGet")
    public void testNullParamGet(@Valid Cat cat) throws Exception{
        System.out.println(cat.toString());
    }

}

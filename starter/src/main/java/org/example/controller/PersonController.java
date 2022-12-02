package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.util.enums.Person;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author: zyh
 * @date: 2022/10/29
 */
@Api(tags = "PERSON")
@RestController
@RequestMapping("/persons")
public class PersonController {

    @ApiOperation("CREATE")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody Person person) {
        // ...
        System.out.println(" persong :"+person);
    }

    @ApiOperation("accounts")
    @RequestMapping(value = "/helloWorld")
    public String helloWorld() {
        return "helloWorld";
    }

    /**
     * @ModelAttribute 是在*controller类方法执行之前调用，因为controller类映射多个url方法时候，慎用
     * @param abc
     * @param model
     */

    @ModelAttribute
    public void populateModel(@RequestParam String abc, Model model) {
        model.addAttribute("attributeName", abc);
    }
}

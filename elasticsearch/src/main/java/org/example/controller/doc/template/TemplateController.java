package org.example.controller.doc.template;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.example.Esdao.EmployeeRepository;
import org.example.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author: zyh
 * @date: 2022/12/7
 */
@Api(tags = "TEMPLATE")
@RestController
@RequestMapping("/template")
public class TemplateController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @ApiOperation(value = "分页",notes = "分页查询")
    @PostMapping("page/{index}")
    @ApiImplicitParam(value = "索引名称", name = "index",dataTypeClass = String.class)
    public Page searchPageTemplate(@PathVariable("index") String index, @RequestBody Map map, @RequestParam Integer current, @RequestParam Integer size) throws Exception{
        Page<Employee> pages = employeeRepository.findAll(PageRequest.of(current,size));
        return pages;
    }

}

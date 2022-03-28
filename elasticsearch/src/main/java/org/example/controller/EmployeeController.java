package org.example.controller;

import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.Esdao.EmployeeRepository;
import org.example.dao.ProductDao;
import org.example.entity.Employee;
import org.example.entity.Product;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zyh
 * @date: 2022/3/7
 */
@Api(tags = "ES")
@RestController
@RequestMapping("/es")
public class EmployeeController {

    @Resource
    private EmployeeRepository employeeRepository;

    @Resource
    private ProductDao productDao;
    /**
     * @return
     * @RequestBody Employee employee
     * 添加
     */
    @ApiOperation("添加")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(@RequestBody Employee employee) {
        if (employee == null) {
            employee = new Employee();
            employee.setId("1");
            employee.setFirstName("xuxu");
            employee.setLastName("zh");
            employee.setAge(26);
            employee.setAbout("i am in peking");
        }
        employeeRepository.save(employee);
        System.err.println("add a obj");
        return "success";
    }

    /**
     * 删除
     *
     * @return
     */
    @ApiOperation("删除")
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public String delete(@RequestParam String id) {
        Employee employee = employeeRepository.queryEmployeeById(id);
        employeeRepository.delete(employee);
        return "success";
    }

    @ApiOperation("局部更新")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@RequestBody Employee em) {
        Employee employee = employeeRepository.queryEmployeeById(em.getId());
        employee.setFirstName(em.getFirstName());
        employeeRepository.save(employee);
        System.err.println("update a obj");
        return "success";
    }

    /**
     * 查询
     *
     * @return
     */
    @ApiOperation("查询")
    @RequestMapping(value = "query", method = RequestMethod.GET)
    public Employee query(@RequestParam String id) {
        Employee accountInfo = employeeRepository.queryEmployeeById(id);
        System.err.println(new Gson().toJson(accountInfo));
        return accountInfo;
    }

    /**
     * 查询所有
     *
     * @return
     */
    @ApiOperation("查询所有1")
    @RequestMapping(value = "/findAll/1", method = RequestMethod.GET)
    public Iterable<Employee> findAll1() {
        Iterable<Employee> iterable = employeeRepository.findAll();
        return iterable;
    }


    /**
     * 查询所有
     *
     * @return
     */
    @ApiOperation("查询所有")
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<Employee> findAll() {
        Iterable<Employee> iterable = employeeRepository.findAll();
        List<Employee> list = new ArrayList<>();
        for (Employee employee : iterable) {
            list.add(employee);
        }
        return list;
    }


    /**
     * 分页
     */
    @ApiOperation("分页")
    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    public Page<Employee> findPage(@RequestParam int page, @RequestParam int size) {
        Page<Employee> pages = employeeRepository.findAll(PageRequest.of(page, size));
        return pages;
    }

    /**
     * sort 不能是字符串字段
     * @param page
     * @param size
     * @param sort
     * @return
     */
    @ApiOperation("分页有排序")
    @RequestMapping(value = "/findPage/sort", method = RequestMethod.GET)
    public Page<Employee> findPage(@RequestParam int page, @RequestParam int size, @RequestParam(required = false) String sort) {
        Product product = productDao.selectById(1);
        Page<Employee> pages = employeeRepository.findAll(PageRequest.of(page, size, Sort.by(sort)));
        return pages;
    }

    /**
     *
     */
    @ApiOperation("计算查询")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public long countFirstName() {
        return employeeRepository.countByFirstName("sam");
    }

    /**
     *
     */
    @ApiOperation("派生类删除")
    @RequestMapping(value = "/removeById", method = RequestMethod.DELETE)
    public List<Employee> removeById(@RequestParam String id) {
        return employeeRepository.removeById(id);
    }

    /**
     * 派生类删除
     */
    @ApiOperation("派生类删除")
    @RequestMapping(value = "/removeByFirstName", method = RequestMethod.DELETE)
    public List<Employee> removeByFirstName(@RequestParam String firstName) {
        return employeeRepository.removeByFirstName(firstName);
    }

    @ApiOperation("搜索名称")
    @RequestMapping(value = "/pageFindByFirstName", method = RequestMethod.GET)
    public Page<Employee> pageFindByFirstName(@RequestParam String firstName) {

        return employeeRepository.findByFirstName(firstName, Pageable.unpaged().first());
    }

    /**
     * 有错误
     */
    @ApiOperation("错误演示")
    @RequestMapping(value = "/pageFindByLastName", method = RequestMethod.GET)
    public List<Employee> pageFindByLastName(@RequestParam String lastName) {
        Slice<Employee> slices = employeeRepository.findByLastName(lastName, Pageable.unpaged().next());
        return slices.getContent();
    }

    @ApiOperation("搜索about")
    @RequestMapping(value = "/pageFindByAbout", method = RequestMethod.GET)
    public List<Employee> pageFindByAbout(@RequestParam String about) {
        return employeeRepository.findByAbout(about, Sort.unsorted());
    }

    @ApiOperation("搜索age")
    @RequestMapping(value = "/pageFindByAge", method = RequestMethod.GET)
    public List<Employee> pageFindByAge(@RequestParam int age) {
        return employeeRepository.findByAge(age, Pageable.unpaged());
    }

    @ApiOperation("限制查询结果")
    @RequestMapping(value = "/findFirstByOrderByLastNameAsc", method = RequestMethod.GET)
    public Employee findFirstByOrderByLastNameAsc(@RequestParam String lastName) {
        Employee employee = employeeRepository.findFirstByLastName(lastName);
        return employee;
    }

}

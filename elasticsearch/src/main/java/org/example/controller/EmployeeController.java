package org.example.controller;

import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.dao.EmployeeRepository;
import org.example.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    private EmployeeRepository employeeRepository;

    /**@RequestBody Employee employee
     * 添加
     * @return
     */
    @ApiOperation("添加")
    @RequestMapping("add")
    public String add() {
        Employee employee = null;
        if (employee == null){
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
     * @return
     */
    @RequestMapping("delete")
    public String delete(@RequestParam String id) {
        Employee employee = employeeRepository.queryEmployeeById(id);
        employeeRepository.delete(employee);
        return "success";
    }

    /**
     * 局部更新
     * @return
     */
    @RequestMapping("update")
    public String update(@RequestBody Employee em) {
        Employee employee = employeeRepository.queryEmployeeById(em.getId());
        employee.setFirstName(em.getFirstName());
        employeeRepository.save(employee);
        System.err.println("update a obj");
        return "success";
    }
    /**
     * 查询
     * @return
     */
    @RequestMapping("query")
    public Employee query(@RequestParam String id) {
        Employee accountInfo = employeeRepository.queryEmployeeById(id);
        System.err.println(new Gson().toJson(accountInfo));
        return accountInfo;
    }

    /**
     * 查询所有
     * @return
     */
    @RequestMapping("/findAll/1")
    public Iterable<Employee> findAll2(){
        Iterable<Employee> iterable = employeeRepository.findAll();
        return iterable;
    }


    /**
     * 查询所有
     * @return
     */
    @RequestMapping("/findAll")
    public List<Employee> findAll(){
        Iterable<Employee> iterable = employeeRepository.findAll();
        List<Employee> list = new ArrayList<>();
        for (Employee employee : iterable){
            list.add(employee);
        }
        return list;
    }

    /** 分页*/
    @RequestMapping("/findPage")
    public Page<Employee> findPage(@RequestParam int page, @RequestParam int size){
        Page <Employee> pages = employeeRepository.findAll(PageRequest.of(page, size));
        return pages;
    }

    /** 计算查询*/
    @RequestMapping("/count")
    public long countFirstName(){
        return employeeRepository.countByFirstName("sam");
    }

    /** 派生类删除*/
    @RequestMapping("/removeById")
    public List<Employee> removeById (@RequestParam String id){
        return employeeRepository.removeById(id);
    }

    /** 派生类删除*/
    @RequestMapping("/removeByFirstName")
    public List<Employee> removeByFirstName(@RequestParam String firstName){
        return employeeRepository.removeByFirstName(firstName);
    }

    @RequestMapping("/pageFindByFirstName")
    public Page<Employee> pageFindByFirstName(@RequestParam String firstName){

        return employeeRepository.findByFirstName(firstName, Pageable.unpaged().first());
    }

    /** 有错误 */
    @RequestMapping("/pageFindByLastName")
    public List<Employee> pageFindByLastName(@RequestParam String lastName){
        Slice<Employee> slices = employeeRepository.findByLastName(lastName, Pageable.unpaged().next());
        return slices.getContent();
    }

    @RequestMapping("/pageFindByAbout")
    public List<Employee> pageFindByAbout(@RequestParam String about){
        return employeeRepository.findByAbout(about, Sort.unsorted());
    }

    @RequestMapping("/pageFindByAge")
    public List<Employee> pageFindByAge(@RequestParam int age){
        return employeeRepository.findByAge(age, Pageable.unpaged());
    }

    /** 限制查询结果*/
    @RequestMapping("/findFirstByOrderByLastNameAsc")
    public Employee findFirstByOrderByLastNameAsc(@RequestParam String lastName){
        Employee employee = employeeRepository.findFirstByLastName(lastName);
        return employee;
    }

}

package org.example.Esdao;

import org.example.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: zyh
 * @date: 2022/3/7
 */
@Component
public interface EmployeeRepository extends ElasticsearchRepository<Employee, String> {

    /**
     * 查询雇员信息
     * @param id
     * @return
     */
    Employee queryEmployeeById(String id);

    /**
     * 计算查询
     * @param firstName
     * @return
     */
    long countByFirstName(String firstName);

    /**
     * 派生 删除
     * @param id
     * @return
     */
    List<Employee> removeById(String id);

    /**
     * 派生类删除
     * @param firstName
     * @return
     */
    List<Employee> removeByFirstName(String firstName);

    /**
     * pageable 遍历分页
     *
     * @param firstName
     * @param pageable
     * @return
     */
    Page<Employee> findByFirstName(String firstName, Pageable pageable);

    /**
     * slice 分页
     *
     * @param lastName
     * @param pageable
     * @return
     */
    Slice<Employee> findByLastName(String lastName, Pageable pageable);

    /**
     * 使用sort 排序
     * @param about
     * @param sort
     * @return
     */
    List<Employee> findByAbout(String about, Sort sort);

    /**
     * 分页
     * @param age
     * @param pageable
     * @return
     */
    List<Employee> findByAge(int age,Pageable pageable);

    /**
     * 限制查询结果
     * @param lastName
     * @return
     */
    Employee findFirstByLastName(String lastName);

}

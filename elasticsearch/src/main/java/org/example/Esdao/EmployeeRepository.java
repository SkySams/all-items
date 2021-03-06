package org.example.Esdao;

import org.example.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: zyh
 *
 * @date: 2022/3/7
 */
@Component
public interface EmployeeRepository extends ElasticsearchRepository<Employee, String> {

    /**
     Keyword	Sample	Elasticsearch Query String
     And	findByNameAndPrice	{"bool" : {"must" : [ {"field" : {"name" : "?"}}, {"field" : {"price" : "?"}} ]}}
     Or	findByNameOrPrice	{"bool" : {"should" : [ {"field" : {"name" : "?"}}, {"field" : {"price" : "?"}} ]}}
     Is	findByName	{"bool" : {"must" : {"field" : {"name" : "?"}}}}
     Not	findByNameNot	{"bool" : {"must_not" : {"field" : {"name" : "?"}}}}
     Between	findByPriceBetween	{"bool" : {"must" : {"range" : {"price" : {"from" : ?,"to" : ?,"include_lower" : true,"include_upper" : true}}}}}
     LessThanEqual	findByPriceLessThan	{"bool" : {"must" : {"range" : {"price" : {"from" : null,"to" : ?,"include_lower" : true,"include_upper" : true}}}}}
     GreaterThanEqual	findByPriceGreaterThan	{"bool" : {"must" : {"range" : {"price" : {"from" : ?,"to" : null,"include_lower" : true,"include_upper" : true}}}}}
     Before	findByPriceBefore	{"bool" : {"must" : {"range" : {"price" : {"from" : null,"to" : ?,"include_lower" : true,"include_upper" : true}}}}}
     After	findByPriceAfter	{"bool" : {"must" : {"range" : {"price" : {"from" : ?,"to" : null,"include_lower" : true,"include_upper" : true}}}}}
     Like	findByNameLike	{"bool" : {"must" : {"field" : {"name" : {"query" : "?*","analyze_wildcard" : true}}}}}
     StartingWith	findByNameStartingWith	{"bool" : {"must" : {"field" : {"name" : {"query" : "?*","analyze_wildcard" : true}}}}}
     EndingWith	findByNameEndingWith	{"bool" : {"must" : {"field" : {"name" : {"query" : "*?","analyze_wildcard" : true}}}}}
     Contains/Containing	findByNameContaining	{"bool" : {"must" : {"field" : {"name" : {"query" : "**?**","analyze_wildcard" : true}}}}}
     In	findByNameIn
     (Collection<String>names)	{"bool" : {"must" : {"bool" : {"should" : [ {"field" : {"name" : "?"}}, {"field" : {"name" : "?"}} ]}}}}
     NotIn	findByNameNotIn
     (Collection<String>names)	{"bool" : {"must_not" : {"bool" : {"should" : {"field" : {"name" : "?"}}}}}}
     Near	findByStoreNear	Not Supported Yet !
     True	findByAvailableTrue	{"bool" : {"must" : {"field" : {"available" : true}}}}
     False	findByAvailableFalse	{"bool" : {"must" : {"field" : {"available" : false}}}}
     OrderBy	findByAvailable
     TrueOrderByNameDesc	{"sort" : [{ "name" : {"order" : "desc"} }],"bool" : {"must" : {"field" : {"available" : true}}}}

     ????????????????????????????????????????????????
     https://blog.csdn.net/Seeyou_y/article/details/99207283

     */

    /**
     * ??????????????????
     * @param id
     * @return
     */
    Employee queryEmployeeById(String id);

    /**
     * ????????????
     * @param firstName
     * @return
     */
    long countByFirstName(String firstName);

    /**
     * ?????? ??????
     * @param id
     * @return
     */
    List<Employee> removeById(String id);

    /**
     * ???????????????
     * @param firstName
     * @return
     */
    List<Employee> removeByFirstName(String firstName);

    /**
     * pageable ????????????
     *
     * @param firstName
     * @param pageable
     * @return
     */
    Page<Employee> findByFirstName(String firstName, Pageable pageable);

    /**
     * ????????????
     * @param firstName
     * @param LastName
     * @param pageable
     * @return
     */
    Page<Employee> findByFirstNameOrLastName(String firstName, String LastName,Pageable pageable);

    /**
     * slice ??????
     *
     * @param lastName
     * @param pageable
     * @return
     */
    Slice<Employee> findByLastName(String lastName, Pageable pageable);

    /**
     * ??????sort ??????
     * @param about
     * @param sort
     * @return
     */
    List<Employee> findByAbout(String about, Sort sort);

    /**
     * ??????
     * @param age
     * @param pageable
     * @return
     */
    List<Employee> findByAge(int age,Pageable pageable);

    /**
     * ??????????????????
     * @param lastName
     * @return
     */
    Employee findFirstByLastName(String lastName);

    /**
     * ??? ??????????????????
     * @param lastName
     * @return
     */
    List<Employee> findByLastNameLike(String lastName);

    List<Employee> findByLastNameContaining(String lastName);

}

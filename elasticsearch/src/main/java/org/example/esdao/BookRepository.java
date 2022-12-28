package org.example.esdao;

import org.example.entity.es.BookEs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * @author: zyh
 * @date: 2022/12/27
 */
@Component
public interface BookRepository extends ElasticsearchRepository<BookEs,String> {

    // https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#elasticsearch.operations.criteriaquery

    /**
     *
     * @param name
     * @return
     */
    @Query("{\"match\": {\"name\": {\"query\": \"?0\"}}}")
    Page<BookEs> findByName(String name, Pageable pageable);

    @Query("{\"ids\": {\"values\": ?0 }}")
    List<BookEs> getByIds(Collection<String> ids);



    /**
     * AND
     * @param name
     * @param price
     * @return
     */
    List<BookEs> findByNameAndPrice(String name, Integer price);

    /**
     * OR
     * @param name
     * @param price
     * @return
     */
    List<BookEs> findByNameOrPrice(String name,Integer price);

    /**
     * Is
     * @param name
     * @return
     */
    List<BookEs> findByName(String name);

    /**
     * Not
     * @param name
     * @return
     */
    List<BookEs> findByNameNot (String name);

    /**
     * between
     * @param price
     * @return
     */
    List<BookEs> findByPriceBetween(Integer price);

    /**
     * LessThan
     * @param price
     * @return
     */
    List<BookEs> findByPriceLessThan (Integer price);

    /**
     * LessThanEqual
     * @param price
     * @return
     */
    List<BookEs> findByPriceLessThanEqual(Integer price);

    /**
     * GreaterThan
     * @param price
     * @return
     */
    List<BookEs> findByPriceGreaterThan (Integer price);

    /**
     * Before
     * @param price
     * @return
     */
    List<BookEs> findByPriceBefore (Integer price);

    /**
     * After
     * @param price
     * @return
     */
    List<BookEs> findByPriceAfter(Integer price);

    /**
     * Like
     * @param name
     * @return
     */
    List<BookEs> findByNameLike(String name);


    /**
     * StartingWith
     * @param name
     * @return
     */
    List<BookEs> findByNameStartingWith (String name);

    /**
     * EndingWith
     * @param name
     * @return
     */
    List<BookEs> findByNameEndingWith(String name);

    /**
     *  Contains/Containing
     * @param name
     * @return
     */
    List<BookEs> findByNameContaining(String name);

    /**
     * In
     * @param names
     * @return
     */
    List<BookEs> findByNameIn(Collection<String> names);

    /**
     * NotIn
     * @param names
     * @return
     */
    List<BookEs> findByNameNotIn(Collection<String>names);


    /**
     * true
     * @param available
     * @return
     */
//    boolean findByAvailableTrue(String available);

    /**
     * False
     * @param available
     * @return
     */
//    boolean findByAvailableFalse(String available);

    /**
     * OrderBy
     * @param available
     * @return
     */
//    List<BookEs> findByAvailableTrueOrderByNameDesc(String available);

    /**
     * Exists
     * @param name
     * @return
     */
    List<BookEs> findByNameExists(String name);

    /**
     * IsNull
     * @param name
     * @return
     */
    List<BookEs> findByNameIsNull(String name);

    /**
     * IsNotNull
     * @param name
     * @return
     */
    List<BookEs> findByNameIsNotNull (String name);

    /**
     * IsEmpty
     * @param name
     * @return
     */
    List<BookEs> findByNameIsEmpty (String name);

    /**
     * IsNotEmpty
     * @param name
     * @return
     */
    List<BookEs> findByNameIsNotEmpty(String name);



}

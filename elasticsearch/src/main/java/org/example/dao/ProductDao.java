package org.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ProductDao继承基类
 */
@Repository
public interface ProductDao extends BaseMapper<Product> {

    /**
     * 全量插入,等价于insert
     *
     * @param entityList
     * @return
     */
    int insertBatchSomeColumn(List<Product> entityList);

}
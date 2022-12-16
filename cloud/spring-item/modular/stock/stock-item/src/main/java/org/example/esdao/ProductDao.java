package org.example.esdao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.entity.Product;
import org.springframework.stereotype.Repository;

/**
 * ProductDao继承基类
 */
@Repository
public interface ProductDao extends BaseMapper<Product> {
}
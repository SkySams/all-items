package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.entity.Product;

import java.util.List;

/**
 * @author: zyh
 * @date: 2022/3/28
 */
public interface ProductService extends IService<Product> {

    Integer insertBatchSomeColumn(List<Product> list);

}

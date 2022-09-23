package org.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.entity.Product;
import org.example.entity.dto.ProductDto;

import java.util.List;

/**
 * @author: zyh
 * @date: 2022/3/28
 */
public interface ProductService extends IService<Product> {

    Integer insertBatchSomeColumn(List<Product> list);

    Page<Product> page();

    PageDTO<ProductDto> pageDto(int current, int size);

}

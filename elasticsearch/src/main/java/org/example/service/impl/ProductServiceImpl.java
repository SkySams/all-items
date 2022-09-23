package org.example.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dao.ProductDao;
import org.example.entity.Product;
import org.example.entity.dto.ProductDto;
import org.example.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: zyh
 * @date: 2022/3/28
 */
@Service("productService")
public class ProductServiceImpl extends ServiceImpl<ProductDao, Product> implements ProductService {

    @Override
    public Integer insertBatchSomeColumn(List<Product> list) {
        return this.baseMapper.insertBatchSomeColumn(list);
    }

    @Override
    public Page<Product> page() {
        Page<Product> page = new Page<>(1,2);
//        page.setOptimizeCountSql(false);
//        page.setTotal(10);
         page = this.baseMapper.page(page);
        return page;
    }

    @Override
    public PageDTO<ProductDto> pageDto(int current, int size) {
       Page<ProductDto> page = Page.of(current,size);
        return new PageDTO<>();
    }
}

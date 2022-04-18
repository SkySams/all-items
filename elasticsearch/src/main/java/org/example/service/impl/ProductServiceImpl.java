package org.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dao.ProductDao;
import org.example.entity.Product;
import org.example.service.ProductService;
import org.springframework.stereotype.Service;

/**
 * @author: zyh
 * @date: 2022/3/28
 */
@Service("productService")
public class ProductServiceImpl extends ServiceImpl<ProductDao, Product> implements ProductService {
}

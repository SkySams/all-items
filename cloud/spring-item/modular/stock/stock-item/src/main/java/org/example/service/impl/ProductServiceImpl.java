package org.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dao.ProductDao;
import org.example.entity.Product;
import org.example.service.ProductService;
import org.springframework.stereotype.Service;

@Service("product")
public class ProductServiceImpl extends ServiceImpl<ProductDao, Product> implements ProductService {
}

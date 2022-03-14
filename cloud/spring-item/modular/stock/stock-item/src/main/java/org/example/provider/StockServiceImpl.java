package org.example.provider;

import org.apache.dubbo.config.annotation.DubboService;
import org.example.entity.Product;
import org.example.service.ProductService;
import org.example.service.StockSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author MK
 */
@DubboService
public class StockServiceImpl implements StockSevice {

    @Autowired
    private ProductService productService;


    @Override
    public String stock() {
        Product product = productService.getById(1);
        return product != null? product.getName()+"stock hello world": "null";
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void reduceStock() {
        throw new NullPointerException ();
    }
}

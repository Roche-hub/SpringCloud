package com.itstudy.product.service.impl;

import com.itstudy.product.bean.Product;
import com.itstudy.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;


@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public Product getProduct(Long id) {
        Product product = new Product();
        product.setId(id);
        product.setPrice(new BigDecimal("99"));
        product.setProductName("苹果" + product.getId());
        product.setNum(Math.toIntExact(id));

        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return product;


    }
}

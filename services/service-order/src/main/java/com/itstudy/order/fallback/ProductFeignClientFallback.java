package com.itstudy.order.fallback;

import com.itstudy.order.feign.ProductFeignClient;
import com.itstudy.product.bean.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductFeignClientFallback implements ProductFeignClient {
    @Override
    public Product getProductByID(Long id) {
        Product product = new Product();
        product.setId(id);
        product.setPrice(new BigDecimal("99"));
        product.setProductName("未知商品" + product.getId());
        product.setNum(Math.toIntExact(id));
        return product;

    }
}

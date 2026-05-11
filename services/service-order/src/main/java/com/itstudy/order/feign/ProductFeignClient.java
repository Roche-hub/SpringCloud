package com.itstudy.order.feign;

import com.itstudy.product.bean.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-product")
public interface ProductFeignClient {

    @GetMapping("/product/{id}")
    Product getProductByID(@PathVariable("id") Long id);
}

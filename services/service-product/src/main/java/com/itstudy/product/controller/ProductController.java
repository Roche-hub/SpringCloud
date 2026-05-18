package com.itstudy.product.controller;

import com.itstudy.product.bean.Product;
import com.itstudy.product.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

//@RequestMapping("/api/product")
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable Long id, HttpServletRequest httpServletRequest){
        String header = httpServletRequest.getHeader("X-Token");
        System.out.println("Token : " + header);
        return productService.getProduct(id);
    }
}

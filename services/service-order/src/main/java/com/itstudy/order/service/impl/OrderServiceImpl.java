package com.itstudy.order.service.impl;


import com.itstudy.order.bean.Order;
import com.itstudy.order.feign.ProductFeignClient;
import com.itstudy.product.bean.Product;
import com.itstudy.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    RestTemplate restTemplate;
    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    ProductFeignClient productFeignClient;
    @Override
    public Order createOrder(Long productId, Long userId) {

        //Product product = ProductGetProductFromRemoteBalancerAnnotation(productId);
        Product product = productFeignClient.getProductByID(productId);

        Order order = new Order();
        order.setId(1L);

        order.setTotalAmount(product.getPrice().multiply(new BigDecimal(product.getNum())));
        order.setUserId(userId);
        order.setNickName("李白");
        order.setAddress("长安");

        order.setProductList(List.of(product));
        return order;

    }

    private Product ProductGetProductFromRemoteBalancerAnnotation(Long productId){

        String url = "http://service-product/product/" + productId;

        log.info("基于注解负载均衡远程请求：{}",url);
        return restTemplate.getForObject(url, Product.class);
    }

    private Product ProductGetProductFromRemoteBalancer(Long productId){

        ServiceInstance serviceInstance = loadBalancerClient.choose("service-product");
        String url = "http://" + serviceInstance.getHost() + ":" +
                serviceInstance.getPort() + "/product/" + productId;

        log.info("负载均衡远程请求：{}",url);
        return restTemplate.getForObject(url, Product.class);
    }
    private Product getProductFromRemote(Long productId){

        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");

        ServiceInstance serviceInstance = instances.get(0);

        String url = "http://" + serviceInstance.getHost() + ":" +
                serviceInstance.getPort() + "/product/" + productId;

        log.info("远程请求：{}",url);
        return restTemplate.getForObject(url, Product.class);

    }
}

package com.itstudy.order.controller;


import com.itstudy.order.bean.Order;
import com.itstudy.order.properties.OrderProperties;
import com.itstudy.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderProperties orderProperties;

    @GetMapping("/create")
    public Order createOrder(@RequestParam("userId") Long userId,
                             @RequestParam("productId") Long productId){
        return orderService.createOrder(productId, userId);

    }

    @GetMapping("/config")
    public String config(){
        return "orderTimeout" + ":" +orderProperties.getTimeout() +
                " orderConfirm" + ":" + orderProperties.getConfirm() +
                " url" + ":" + orderProperties.getDbUrl();
    }
}

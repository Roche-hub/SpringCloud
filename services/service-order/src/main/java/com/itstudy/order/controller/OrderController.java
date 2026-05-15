package com.itstudy.order.controller;


import com.itstudy.order.bean.Order;
import com.itstudy.order.properties.OrderProperties;
import com.itstudy.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
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

    @GetMapping("/seckill")
    public Order seckill(@RequestParam("userId") Long userId,
                             @RequestParam("productId") Long productId){
        Order order = orderService.createOrder(productId, userId);
        order.setId(Long.MAX_VALUE);
        return order;
    }

    @GetMapping("/config")
    public String config(){
        return "orderTimeout" + ":" +orderProperties.getTimeout() +
                " orderConfirm" + ":" + orderProperties.getConfirm() +
                " url" + ":" + orderProperties.getDbUrl();
    }

    @GetMapping("/writeDb")
    public String writeDb(){
        log.info("writeDb.....");
        return "writeDb success .......";
    }

    @GetMapping("/readDb")
    public String readDb(){
        log.info("readDb.....");
        return "readDb success .......";
    }
}

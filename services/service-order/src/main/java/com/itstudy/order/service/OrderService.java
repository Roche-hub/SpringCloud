package com.itstudy.order.service;


import com.itstudy.order.bean.Order;

public interface OrderService {
    Order createOrder(Long productId, Long userId);
}

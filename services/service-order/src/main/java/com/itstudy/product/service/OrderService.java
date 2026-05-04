package com.itstudy.product.service;


import com.itstudy.order.bean.Order;

public interface OrderService {
    Order createOrder(Long productId, Long userId);
}

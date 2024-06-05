package com.sspu.service;

import com.sspu.domain.orders.Orders;

import java.util.List;

public interface OrdersService {

    List<Orders> getAllOrders(Integer id);
    boolean deleteOrder(Integer userId, String orderId);
}

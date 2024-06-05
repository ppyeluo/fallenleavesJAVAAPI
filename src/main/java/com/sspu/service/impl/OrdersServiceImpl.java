package com.sspu.service.impl;

import com.sspu.dao.OrdersMapper;
import com.sspu.domain.orders.Orders;
import com.sspu.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService{
    @Autowired
    private OrdersMapper ordersMapper;

    public List<Orders> getAllOrders(Integer id){
        return ordersMapper.findByUserId(id);
    }
    public
    boolean deleteOrder(Integer userId, String orderId){
        Orders order = ordersMapper.findByIdAndUserId(orderId, userId);
        if (order == null) {
            return false;
        }
        int deleteResult = ordersMapper.deleteById(orderId);
        return deleteResult > 0;
    }
}

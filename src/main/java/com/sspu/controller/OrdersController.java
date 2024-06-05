package com.sspu.controller;

import com.sspu.domain.user.User;
import com.sspu.domain.orders.Orders;
import com.sspu.service.OrdersService;
import com.sspu.utils.JWTUtil;
import com.sspu.utils.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    @GetMapping("/allOrders")
    public ResponseEntity allOrders(@RequestHeader("Authorization") String authHeader){
        // 从 authHeader 中提取 token
        String token = extractTokenFromHeader(authHeader);
        User user = JWTUtil.decodeToken(token);
        if(token == "") {
            return ResponseHelper.sendResponse(401, null);
        }
        List<Orders> list = ordersService.getAllOrders(user.getId());
        return ResponseHelper.sendResponse(200, list);
    }
    @DeleteMapping("/deleteOrder/{id}")
    public ResponseEntity deleteOrder(@RequestHeader("Authorization") String authHeader
            , @PathVariable("id") String orderId){
        // 从 authHeader 中提取 token
        String token = extractTokenFromHeader(authHeader);
        User user = JWTUtil.decodeToken(token);
        if(token == "") {
            return ResponseHelper.sendResponse(401, null);
        }
        boolean success = ordersService.deleteOrder(user.getId(), orderId);
        if (success) {
            return ResponseHelper.sendResponse(200, null, "删除成功！");
        } else {
            return ResponseHelper.sendResponse(400, null,"删除失败！");
        }
    }

    private String extractTokenFromHeader(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }
}

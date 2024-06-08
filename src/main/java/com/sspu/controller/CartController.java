package com.sspu.controller;

import com.sspu.domain.cart.Cart;
import com.sspu.domain.user.User;
import com.sspu.domain.cart.AddCartRequest;
import com.sspu.domain.cart.BatchRemoveCartRequest;
import com.sspu.domain.cart.RemoveCartRequest;
import com.sspu.service.CartService;
import com.sspu.utils.JWTUtil;
import com.sspu.utils.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CartController {
    @Autowired
    private CartService cartService;

    // 得到购物车内详细信息
    @GetMapping("/cart")
    public ResponseEntity getCartDetailed(@RequestHeader("Authorization") String authHeader){
        // 从 authHeader 中提取 token
        String token = extractTokenFromHeader(authHeader);
        User user = JWTUtil.decodeToken(token);
        if(token == "") {
            return ResponseHelper.sendResponse(401, null);
        }
        List<Cart> cartItems = cartService.getCartDetailed(user.getId());
        return ResponseHelper.sendResponse(200, cartItems);
    }
    // 向购物车内添加商品
    @PostMapping("/addCart")
    public ResponseEntity addCart(@RequestHeader("Authorization") String authHeader,
                                  @RequestBody AddCartRequest request){
        // 从 authHeader 中提取 token
        String token = extractTokenFromHeader(authHeader);
        User user = JWTUtil.decodeToken(token);
        if(token == "") {
            return ResponseHelper.sendResponse(401, null);
        }
        boolean success = cartService.addCart(user.getId(), request.getId(), request.getCount());
        if (success) {
            return ResponseHelper.sendResponse(200, null, "加入购物车成功");
        } else {
            return ResponseHelper.sendResponse(400, null, "加入购物车失败");
        }
    }
    // 删除购物车内的商品
    @DeleteMapping("/removeCart")
    public ResponseEntity removeCart(@RequestHeader("Authorization") String authHeader,
                                     @RequestBody RemoveCartRequest request){
        // 从 authHeader 中提取 token
        String token = extractTokenFromHeader(authHeader);
        User user = JWTUtil.decodeToken(token);
        if(token == "") {
            return ResponseHelper.sendResponse(401, null);
        }
        boolean success = cartService.removeCart(user.getId(), request.getId(), request.getCount());
        if (success) {
            return ResponseHelper.sendResponse(200, null, "删除成功");
        } else {
            return ResponseHelper.sendResponse(400, null, "删除失败");
        }
    }
    // 批量删除购物车内的商品
    @DeleteMapping("/batchRemoveCart")
    public ResponseEntity batchRemoveCart(@RequestHeader("Authorization") String authHeader,
                                          @RequestBody BatchRemoveCartRequest request){
        // 从 authHeader 中提取 token
        String token = extractTokenFromHeader(authHeader);
        User user = JWTUtil.decodeToken(token);
        if(token == "") {
            return ResponseHelper.sendResponse(401, null);
        }
        int deletedCount = cartService.batchRemoveCart(user.getId(), request.getNumberList());
        Map<String, Integer> response = new HashMap<>();
        response.put("deletedCount", deletedCount);
        return ResponseHelper.sendResponse(200,null);
    }

    private String extractTokenFromHeader(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }
}

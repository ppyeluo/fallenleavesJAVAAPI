package com.sspu.service;

import com.sspu.domain.cart.Cart;

import java.util.List;

public interface CartService {
    // 得到购物车内详细信息
    List<Cart> getCartDetailed(Integer userId);
    // 向购物车内添加商品
    boolean addCart(Integer userId, String commodityId, int count);
    // 删除购物车内的商品
    boolean removeCart(Integer userId, String commodityId, int count);
    // 批量删除购物车内的商品
    int batchRemoveCart(Integer userId, List<String> commodityIds);
}

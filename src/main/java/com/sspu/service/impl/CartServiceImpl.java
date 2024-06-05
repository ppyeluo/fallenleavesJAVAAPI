package com.sspu.service.impl;

import com.sspu.dao.CartMapper;
import com.sspu.domain.cart.Cart;
import com.sspu.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    private CartMapper cartMapper;

    @Override
    public List<Cart> getCartDetailed(Integer userId){
        return cartMapper.getCartDetailsByUserId(userId);
    }
    @Override
    public boolean addCart(Integer userId, String commodityId, int count){
        Integer existingCount = cartMapper.getCountByUserIdAndCommodityId(userId, commodityId);
        if (existingCount == null) {
            int insertResult = cartMapper.insert(count, userId, commodityId);
            return insertResult > 0;
        } else {
            int updateResult = cartMapper.updateCount(count, userId, commodityId);
            return updateResult > 0;
        }
    }
    @Override
    public boolean removeCart(Integer userId, String commodityId, int count){
        Integer existingCount = cartMapper.getCountByUserIdAndCommodityId(userId, commodityId);
        if (existingCount == null || existingCount < count) {
            return false;
        } else if (existingCount == count) {
            int deleteResult = cartMapper.deleteByUserIdAndCommodityId(userId, commodityId);
            return deleteResult > 0;
        } else {
            int decreaseResult = cartMapper.decreaseCount(count, userId, commodityId);
            return decreaseResult > 0;
        }
    }

    @Override
    public int batchRemoveCart(Integer userId, List<String> commodityIds) {
        String ids = String.join(",", commodityIds.stream().map(String::valueOf).toArray(String[]::new));
        int deletedCount = cartMapper.batchDelete(userId, ids);
        return deletedCount;
    }
}

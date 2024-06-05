package com.sspu.dao;

import com.sspu.domain.cart.Cart;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CartMapper {
    @Select("SELECT cart.*, commodity.name, commodity.picture, commodity.flowerLanguage, commodity.price, commodity.bank " +
            "FROM cart " +
            "JOIN commodity ON cart.commodityId = commodity.id " +
            "WHERE cart.userId = #{userId}")
    List<Cart> getCartDetailsByUserId(@Param("userId") Integer userId);
    //
    @Select("SELECT count FROM cart WHERE userId = #{userId} AND commodityId = #{commodityId}")
    Integer getCountByUserIdAndCommodityId(@Param("userId") Integer userId, @Param("commodityId") String commodityId);

    @Insert("INSERT INTO cart (count, userId, commodityId) VALUES (#{count}, #{userId}, #{commodityId})")
    int insert(@Param("count") int count, @Param("userId") Integer userId, @Param("commodityId") String commodityId);

    @Update("UPDATE cart SET count = count + #{count} WHERE userId = #{userId} AND commodityId = #{commodityId}")
    int updateCount(@Param("count") int count, @Param("userId") Integer userId, @Param("commodityId") String commodityId);

    //
    @Update("UPDATE cart SET count = count - #{count} WHERE userId = #{userId} AND commodityId = #{commodityId}")
    int decreaseCount(@Param("count") int count, @Param("userId") Integer userId, @Param("commodityId") String commodityId);

    @Delete("DELETE FROM cart WHERE userId = #{userId} AND commodityId = #{commodityId}")
    int deleteByUserIdAndCommodityId(@Param("userId") Integer userId, @Param("commodityId") String commodityId);
    //
    @Delete("DELETE FROM cart WHERE userId = #{userId} AND commodityId IN (${ids})")
    int batchDelete(@Param("userId") Integer userId, @Param("ids") String ids);
}

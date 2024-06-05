package com.sspu.dao;

import com.sspu.domain.orders.Orders;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.LocalDateTimeTypeHandler;

import java.util.List;

public interface OrdersMapper {
    @Select("SELECT o.*, c.name, c.picture " +
            "FROM orders o " +
            "JOIN commodity c ON o.commodityId = c.id " +
            "WHERE o.userId = #{id}")
    List<Orders> findByUserId(@Param("id") Integer id);
    @Select("SELECT * FROM orders WHERE id = #{orderId} AND userId = #{userId}")
    Orders findByIdAndUserId(@Param("orderId") String orderId, @Param("userId") Integer userId);

    @Delete("DELETE FROM orders WHERE id = #{orderId}")
    int deleteById(@Param("orderId") String orderId);

}

package com.sspu.dao;

import com.sspu.domain.collect.Collect;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CollectMapper {
    // 得到我的收藏中的所有商品
    @Select("SELECT collect.*, commodity.name, commodity.flowerLanguage, commodity.picture FROM collect INNER JOIN commodity ON collect.commodityId = commodity.id WHERE collect.userId = #{id}")
    List<Collect> getCollect(Integer id);
    // 查找商品是否在收藏中
    @Select("SELECT * FROM collect WHERE userId = #{userId} AND commodityId = #{commodityId}")
    Collect findByUserIdAndCommodityId(@Param("userId") Integer userId, @Param("commodityId") String commodityId);
    // 向我的收藏中添加商品
    @Insert("INSERT INTO collect (userId, commodityId) VALUES (#{userId}, #{commodityId})")
    int insert(@Param("userId") Integer userId, @Param("commodityId") String commodityId);
    // 从我的收藏中删除商品
    @Delete("DELETE FROM collect WHERE userId = #{userId} AND commodityId = #{commodityId}")
    int deleteByUserIdAndCommodityId(@Param("userId") Integer userId, @Param("commodityId") String commodityId);
}

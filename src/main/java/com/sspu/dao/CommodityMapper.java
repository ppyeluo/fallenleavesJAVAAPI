package com.sspu.dao;


import com.sspu.domain.commodity.Commodity;
import com.sspu.domain.commodity.CommodityComment;
import com.sspu.domain.commodity.Type;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CommodityMapper{
    @Select("      SELECT  t.type, t.typeName, COUNT(c.type) AS typeCount\n" +
            "        FROM type t LEFT JOIN commodity c ON t.type = c.type\n" +
            "        GROUP BY t.type, t.typeName")
    List<Type> getType();
    // 根据编号获取花的详细信息

    @Select("SELECT commodity.* " +
            "FROM commodity " +
            "WHERE commodity.id = #{id} " )
    Commodity queryCommodityDetailed(@Param("id") String id);
    // 根据输入的关键字检索对应类型名称包含或者名称包含或者花语包含关键字的商品
    @Select("SELECT * FROM commodity WHERE name LIKE #{queryParam} OR flowerLanguage LIKE #{queryParam} LIMIT #{limit}")
    List<Commodity> getIntentResult(@Param("queryParam") String queryParam, @Param("queryParam") String queryParam2, @Param("limit") int limit);
    // 得到搜索框热门搜索数据
    @Select("SELECT * FROM commodity ORDER BY sold DESC LIMIT 10")
    List<Commodity> getHotSearch();
    // 得到最新上架的6件商品
    @Select("SELECT * FROM commodity " +
            "WHERE #{type} IS NULL OR type = #{type} " +
            "ORDER BY listing_time DESC LIMIT #{limit}")
    List<Commodity> getNewLaunch(@Param("type") String type, @Param("limit") int limit);
    // 得到售出量最多的5件商品
    @Select("SELECT * FROM commodity ORDER BY sold DESC LIMIT 5")
    List<Commodity> getHotBuy();
    // 得到热门推荐的9条商品
    @Select("SELECT * FROM commodity ORDER BY bank DESC LIMIT ${start}, 12")
    List<Commodity> getHotRecommend(@Param("start") Integer start);
    // 得到搜索页数据（带分页）
    @Select("SELECT COUNT(*) as total "
            + "FROM commodity c "
            + "JOIN type t ON c.type = t.type "
            + "WHERE (c.name LIKE #{intent} OR c.flowerLanguage LIKE #{intent} OR c.desc LIKE #{intent} OR t.typeName LIKE #{intent}) "
            + "AND (c.type = #{type} OR #{type} = '')")
    int countSearchCommodity(@Param("intent") String intent, @Param("type") String type);

    @Select("SELECT c.* "
            + "FROM commodity c "
            + "JOIN type t ON c.type = t.type "
            + "WHERE (c.name LIKE #{intent} OR c.flowerLanguage LIKE #{intent} OR c.desc LIKE #{intent} OR t.typeName LIKE #{intent}) "
            + "AND (c.type = #{type} OR #{type} = '') "
            + "ORDER BY ${sortField} ${sortOrder} "
            + "LIMIT #{offset}, #{pageSize}")
    List<Commodity> searchCommodity(@Param("intent") String intent, @Param("type") String type,
                                    @Param("offset") int offset, @Param("pageSize") int pageSize,
                                    @Param("sortField") String sortField, @Param("sortOrder") String sortOrder);
    // 返回商品的所有评论，参数为商品id
    @Select("      SELECT \n" +
            "        co.id,\n" +
            "        c.name AS commodityName,\n" +
            "        u.username,\n" +
            "        u.avatar,\n" +
            "        u.tag,\n" +
            "        co.content, \n" +
            "        co.commentTime, \n" +
            "        co.rating, \n" +
            "        co.ipAddress, \n" +
            "        co.likesCount, \n" +
            "        co.repliesCount, \n" +
            "        co.commentStatus\n" +
            "      FROM comments co\n" +
            "      INNER JOIN commodity c ON co.commodityId = c.id\n" +
            "      INNER JOIN user u ON co.userId = u.id\n" +
            "      WHERE co.commodityId = #{commodityId}")
    List<CommodityComment> getCommodityComments(@Param("commodityId") String commodityId);
    // 给商品的评论点赞
    @Update("" +
            "        UPDATE comments \n" +
            "        SET likesCount = likesCount + 1 \n" +
            "        WHERE id = #{commentId}")
    void likeComment(@Param("commentId") Integer commentId);
}

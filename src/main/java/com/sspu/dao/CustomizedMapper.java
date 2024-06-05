package com.sspu.dao;

import com.sspu.domain.customized.CommodityCategory;
import com.sspu.domain.customized.HotSaleCommodity;
import com.sspu.domain.customized.HotSearchKeywords;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CustomizedMapper {
    // 得到所有花的分类名称和库存
    @Select("  SELECT type.typeName, COUNT(commodity.id) AS count\n" +
            "        FROM commodity\n" +
            "        INNER JOIN type ON commodity.type = type.type\n" +
            "        GROUP BY commodity.type, type.typeName  ")
    List<CommodityCategory> getCommodityCategory();

    // 得到热搜关键词和搜索次数
    @Select("        SELECT keyword, count\n" +
            "        FROM hot_search_keywords\n" +
            "        ORDER BY count DESC\n" +
            "        LIMIT 20")
    List<HotSearchKeywords> getHotSearchKeywords();
    // 得到热门销售数据
    @Select("        SELECT name, ROUND(sold * price / 10000, 2) AS dealTotal, sold\n" +
            "        FROM commodity\n" +
            "        ORDER BY dealTotal DESC\n" +
            "        LIMIT 10")
    List<HotSaleCommodity> getHotSaleCommodity();
}

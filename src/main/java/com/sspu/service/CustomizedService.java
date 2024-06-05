package com.sspu.service;

import com.sspu.domain.customized.CommodityCategory;
import com.sspu.domain.customized.HotSaleCommodity;
import com.sspu.domain.customized.HotSearchKeywords;

import java.util.List;

public interface CustomizedService {
    // 得到所有花的分类名称和库存
    List<CommodityCategory> getCommodityCategory();
    // 得到热搜关键词和搜索次数
    List<HotSearchKeywords> getHotSearchKeywords();
    // 得到热门销售数据
    List<HotSaleCommodity> getHotSaleCommodity();
}

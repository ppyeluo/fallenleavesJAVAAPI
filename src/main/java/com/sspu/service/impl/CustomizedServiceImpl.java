package com.sspu.service.impl;

import com.sspu.dao.CustomizedMapper;
import com.sspu.domain.customized.CommodityCategory;
import com.sspu.domain.customized.HotSaleCommodity;
import com.sspu.domain.customized.HotSearchKeywords;
import com.sspu.service.CustomizedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomizedServiceImpl implements CustomizedService {
    @Autowired   //注入DAO对象
    private CustomizedMapper customizedMapper;

    // 得到所有花的分类名称和库存
    @Override
    public List<CommodityCategory> getCommodityCategory() {
        return customizedMapper.getCommodityCategory();
    }

    // 得到热搜关键词和搜索次数

    @Override
    public List<HotSearchKeywords> getHotSearchKeywords(){
        return customizedMapper.getHotSearchKeywords();
    }
    // 得到热门销售数据

    public List<HotSaleCommodity> getHotSaleCommodity(){
        return customizedMapper.getHotSaleCommodity();
    }
}

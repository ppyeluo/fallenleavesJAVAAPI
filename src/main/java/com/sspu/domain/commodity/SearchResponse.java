package com.sspu.domain.commodity;

import com.sspu.domain.commodity.Commodity;

import java.util.List;

// 搜索实时反馈的数据
public class SearchResponse {
    private List<Commodity> commodityList;

    public List<Commodity> getCommodityList() {
        return commodityList;
    }

    public void setCommodityList(List<Commodity> commodityList) {
        this.commodityList = commodityList;
    }
}

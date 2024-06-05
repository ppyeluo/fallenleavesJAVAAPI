package com.sspu.service;

import com.sspu.domain.commodity.*;

import java.util.List;

public interface CommodityService {
    // 返回所有商品的类别(type),按该类数量多少排序
    List<Type> getType();
    // 根据编号获取花的详细信息
    Commodity queryCommodityDetailed(String id);
    // 根据输入的关键字检索对应类型名称包含或者名称包含或者花语包含关键字的商品
    SearchResponse getIntentResult(String intent);
    // 得到搜索框热门搜索数据
    List<Commodity> getHotSearch();
    // 得到最新上架的6件商品
    List<Commodity> getNewLaunch();
    List<Commodity> getNewLaunch(String type);
    // 得到售出量最多的5件商品
    List<Commodity> getHotBuy();
    // 得到热门推荐的9条商品
    List<Commodity> getHotRecommend(Integer page);
    // 得到搜索页数据（带分页）
    SearchResult searchCommodity(String intent, String type, int page, int pageSize, String sortField, String sortOrder);
    // 返回商品的所有评论，参数为商品id
    List<CommodityComment> getCommodityComments(String commodityId);
    // 给商品的评论点赞
    void likeComment(Integer commentId);
}
package com.sspu.service.impl;


import com.sspu.dao.CommodityMapper;
import com.sspu.domain.commodity.*;
import com.sspu.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityServiceImpl implements CommodityService {
    @Autowired   //注入DAO对象
    private CommodityMapper commodityMapper;
    @Override
    public List<Type> getType() {
        return commodityMapper.getType();
    }

    @Override
    public Commodity queryCommodityDetailed(String id) {
        return commodityMapper.queryCommodityDetailed(id);
    }

    @Override
    public SearchResponse getIntentResult(String intent) {
        String queryParam = "%" + intent + "%";

        List<Commodity> commodityList = commodityMapper.getIntentResult(queryParam, queryParam, 4);

        SearchResponse response = new SearchResponse();
        response.setCommodityList(commodityList);
        return response;
    }

    @Override
    public List<Commodity> getHotSearch() {
        return commodityMapper.getHotSearch();
    }

    @Override
    public List<Commodity> getNewLaunch() {
        List<Commodity> newCommodities = commodityMapper.getNewLaunch(null, 6);
        return newCommodities;
    }
    @Override
    public List<Commodity> getNewLaunch(String type) {
        List<Commodity> newCommodities = commodityMapper.getNewLaunch(type, 6);
        return newCommodities;
    }

    @Override
    public List<Commodity> getHotBuy() {
        List<Commodity> list = commodityMapper.getHotBuy();
        return list;
    }

    @Override
    public List<Commodity> getHotRecommend(Integer page) {
        Integer start = (page - 1) * 12;
        List<Commodity> list = commodityMapper.getHotRecommend(start);
        return list;
    }

    @Override
    public SearchResult searchCommodity(String intent, String type, int page, int pageSize, String sortField, String sortOrder) {
        int offset = (page - 1) * pageSize;
        String tempIntent = '%'+intent+'%';
        List<Commodity> commodities = commodityMapper.searchCommodity(tempIntent, type, offset, pageSize, sortField, sortOrder);
        int total = commodityMapper.countSearchCommodity(tempIntent, type);
        int totalPages = (int) Math.ceil((double) total / pageSize);

        SearchResult searchResult = new SearchResult();
        searchResult.setIntent(intent);
        searchResult.setType(type);
        searchResult.setPage(page);
        searchResult.setPageSize(pageSize);
        searchResult.setTotalPages(totalPages);
        searchResult.setTotal(total);
        searchResult.setSortField(sortField);
        searchResult.setSortOrder(sortOrder);
        searchResult.setCommodities(commodities);
        return searchResult;
    }

    @Override
    public List<CommodityComment> getCommodityComments(String commodityId) {
        return commodityMapper.getCommodityComments(commodityId);
    }

    @Override
    public void likeComment(Integer commentId) {
        commodityMapper.likeComment(commentId);
    }
}

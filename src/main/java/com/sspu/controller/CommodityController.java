package com.sspu.controller;

import com.alibaba.druid.util.StringUtils;
import com.sspu.domain.commodity.*;
import com.sspu.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sspu.utils.ResponseHelper;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    @GetMapping("/getType")
    // 返回所有商品的类别(type),按该类数量多少排序
    public ResponseEntity getType(){
        try{
            List<Type> list = commodityService.getType();
            return ResponseHelper.sendResponse(200, list);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseHelper.sendResponse(500, null);
        }
    }

    // 根据编号获取花的详细信息
    @GetMapping("/commodity")
    public ResponseEntity queryCommodityDetailed(@RequestParam String id){
        try{
            Commodity list = commodityService.queryCommodityDetailed(id);
            return ResponseHelper.sendResponse(200, list);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseHelper.sendResponse(500, null);
        }
    }

    // 根据输入的关键字检索对应类型名称包含或者名称包含或者花语包含关键字的商品
    @GetMapping("/getIntentResult")
    public ResponseEntity getIntentResult(@RequestParam String intent){
        if (StringUtils.isEmpty(intent)) {
            return ResponseHelper.sendResponse(400, null);
        }
        SearchResponse response = commodityService.getIntentResult(intent);
        return ResponseHelper.sendResponse(200, response);
    }

    // 得到搜索框热门搜索数据
    @GetMapping("/getHotSearch")
    public ResponseEntity getHotSearch(){
        List<Commodity> hotSearchList = commodityService.getHotSearch();
        return ResponseHelper.sendResponse(200, hotSearchList);
    }

    // 得到最新上架的6件商品
    @GetMapping("/getNewLaunch")
    public ResponseEntity getNewLaunch(@RequestParam(required = false) String type){
        List<Commodity> list = type == "" ? commodityService.getNewLaunch() : commodityService.getNewLaunch(type);
        return ResponseHelper.sendResponse(200, list);
    }

    // 得到售出量最多的5件商品
    @GetMapping("/getHotBuy")
    public ResponseEntity getHotBuy(){
        List<Commodity> list = commodityService.getHotBuy();
        return ResponseHelper.sendResponse(200, list);
    }

    // 得到热门推荐的9条商品
    @GetMapping("/getHotRecommend/{page}")
    public ResponseEntity getHotRecommend(@PathVariable Integer page){
        List<Commodity> list = commodityService.getHotRecommend(page);
        return ResponseHelper.sendResponse(200, list);
    }

    // 得到搜索页数据（带分页）
    @PostMapping("/searchCommodity")
    public ResponseEntity searchCommodity(@RequestBody SearchRequest searchRequest){
        SearchResult searchResult = commodityService.searchCommodity(
                searchRequest.getIntent(),
                searchRequest.getType(),
                searchRequest.getPage(),
                searchRequest.getPageSize(),
                searchRequest.getSortField(),
                searchRequest.getSortOrder());
        return ResponseHelper.sendResponse(200, searchResult);
    }
    // 返回商品的所有评论
    @GetMapping("/getCommodityComments/{id}")
    public ResponseEntity getCommodityComments(@PathVariable("id") String commodityId){
        List<CommodityComment> comments = commodityService.getCommodityComments(commodityId);
        return ResponseHelper.sendResponse(200, comments);
    }
    // 给商品的评论点赞
    @GetMapping("/likeComment/{id}")
    public ResponseEntity likeComment(@PathVariable("id") Integer commentId){
        return ResponseHelper.sendResponse(200, null, "success");
    }
}

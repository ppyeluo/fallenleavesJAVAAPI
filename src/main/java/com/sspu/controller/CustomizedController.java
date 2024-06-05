package com.sspu.controller;

import com.sspu.domain.customized.CommodityCategory;
import com.sspu.domain.customized.HotSaleCommodity;
import com.sspu.domain.customized.HotSearchKeywords;
import com.sspu.service.CustomizedService;
import com.sspu.utils.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomizedController {
    @Autowired
    private CustomizedService customizedService;

    @GetMapping("/getCommodityCategory")
    public ResponseEntity getCommodityCategory(){
        List<CommodityCategory> list = customizedService.getCommodityCategory();
        return ResponseHelper.sendResponse(200, list);
    }
    @GetMapping("/getHotSearchKeywords")
    public ResponseEntity getHotSearchKeywords(){
        List<HotSearchKeywords> list = customizedService.getHotSearchKeywords();
        return ResponseHelper.sendResponse(200, list);
    }
    @GetMapping("/getHotSaleCommodity")
    public ResponseEntity getHotSaleCommodity(){
        List<HotSaleCommodity> list = customizedService.getHotSaleCommodity();
        return ResponseHelper.sendResponse(200, list);
    }
}

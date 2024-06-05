package com.sspu.controller;

import com.sspu.domain.collect.Collect;
import com.sspu.domain.user.User;
import com.sspu.domain.collect.AddCollectRequest;
import com.sspu.domain.collect.AddCollectResponse;
import com.sspu.service.CollectService;
import com.sspu.utils.JWTUtil;
import com.sspu.utils.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CollectController {
    @Autowired
    private CollectService collectService;
    // 得到我的收藏中的所有商品
    @GetMapping("/collect")
    public ResponseEntity getCollect(@RequestHeader("Authorization") String authHeader){
        // 从 authHeader 中提取 token
        String token = extractTokenFromHeader(authHeader);
        if(token == "") {
            return ResponseHelper.sendResponse(401, null);
        }
        User user = JWTUtil.decodeToken(token);
        List<Collect> list = collectService.getCollect(user.getId());
        return ResponseHelper.sendResponse(200, list);
    }
    // 向我的收藏中添加商品
    @PostMapping("/addCollect")
    public ResponseEntity addCollect(@RequestHeader("Authorization") String authHeader,
                                     @RequestBody AddCollectRequest request){
        // 从 authHeader 中提取 token
        String token = extractTokenFromHeader(authHeader);
        if(token == "") {
            return ResponseHelper.sendResponse(401, null);
        }
        User user = JWTUtil.decodeToken(token);
        AddCollectResponse response = collectService.addCollect(request.getUserId(), request.getCommodityId());
        return ResponseHelper.sendResponse(200, response);
    }

    // 从我的收藏中删除商品
    @DeleteMapping("/removeCollect")
    public ResponseEntity removeCollect(@RequestHeader("Authorization") String authHeader,
                                        @PathVariable("commodityId") String commodityId){
        // 从 authHeader 中提取 token
        String token = extractTokenFromHeader(authHeader);
        if(token == "") {
            return ResponseHelper.sendResponse(401, null);
        }
        User user = JWTUtil.decodeToken(token);
        boolean success = collectService.removeCollect(user.getId(), commodityId);
        if (success) {
            return ResponseHelper.sendResponse(200, null, "删除成功");
        } else {
            return ResponseHelper.sendResponse(401, null, "删除失败");
        }
    }

    private String extractTokenFromHeader(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }
}

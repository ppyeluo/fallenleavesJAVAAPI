package com.sspu.service;

import com.sspu.domain.collect.Collect;
import com.sspu.domain.collect.AddCollectResponse;

import java.util.List;

public interface CollectService {
    // 得到我的收藏中的所有商品
    List<Collect> getCollect(Integer id);

    // 向我的收藏中添加商品
    AddCollectResponse addCollect(Integer userId, String commodityId);
    boolean removeCollect(Integer userId, String commodityId);
    // 从我的收藏中删除商品
}

package com.sspu.service.impl;

import com.sspu.dao.CollectMapper;
import com.sspu.domain.collect.Collect;
import com.sspu.domain.collect.AddCollectResponse;
import com.sspu.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectMapper collectMapper;

    // 得到我的收藏中的所有商品
    @Override
    public List<Collect> getCollect(Integer id){
        return collectMapper.getCollect(id);
    }
    // 向我的收藏中添加商品
    @Override
    public AddCollectResponse addCollect(Integer userId, String commodityId){
        Collect collect = collectMapper.findByUserIdAndCommodityId(userId, commodityId);
        if(collect != null){
            return new AddCollectResponse(false);
        }else{
            int insertResult = collectMapper.insert(userId, commodityId);
            if(insertResult > 0){
                return new AddCollectResponse(true);
            }else{
                return new AddCollectResponse(false);
            }
        }
    }
    // 从我的收藏中删除商品
    @Override
    public boolean removeCollect(Integer userId, String commodityId){
        Collect existingCollect = collectMapper.findByUserIdAndCommodityId(userId, commodityId);
        if (existingCollect == null) {
            return false;
        } else {
            int deleteResult = collectMapper.deleteByUserIdAndCommodityId(userId, commodityId);
            return deleteResult > 0;
        }
    }
}
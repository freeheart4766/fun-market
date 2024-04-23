package com.bxt.fm.service.impl;

import com.bxt.fm.mapper.GoodsMapper;
import com.bxt.fm.mapper.StoreInfoMapper;
import com.bxt.fm.pojo.StoreInfo;
import com.bxt.fm.service.StoreInfoService;
import com.bxt.fm.util.Result;
import com.bxt.fm.util.ResultCodeEnum;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("storeInfoService")
public class StoreInfoServiceImpl implements StoreInfoService {
    @Resource(name = "storeInfoMapper")
    private StoreInfoMapper storeInfoMapper;

    @Resource(name = "goodsMapper")
    private GoodsMapper goodsMapper;

    //修改店铺名称
    @Override
    public Result<String> updateStoreName(int sellerId, String storeName) {
        storeInfoMapper.updateStoreName(sellerId,storeName);
        return Result.ok();
    }

    //修改店铺头像
    @Override
    public Result<String> updateStoreAvatar(String picturePath, int sellerId) {
        storeInfoMapper.updateStoreAvatar(picturePath,sellerId);
        return Result.ok();
    }

    //修改店铺封面
    @Override
    public Result<String> updateStoreCover(String picturePath, int sellerId) {
        storeInfoMapper.updateStoreCover(picturePath,sellerId);
        return Result.ok();
    }

    //查询店铺信息
    @Override
    public Result<StoreInfo> getStoreInfo(int sellerId) {
        StoreInfo storeInfo = storeInfoMapper.getStoreInfo(sellerId);
        return Result.ok(storeInfo);
    }

    //主页中展示店铺信息
    @Override
    public Result<StoreInfo> getStoreInfoByCustomer(int goodsId) {
        //由goodsId查询sellerId
        int sellerId = goodsMapper.getGoodsIdBySellerId(goodsId);
        //根据sellerId查询店铺信息
        StoreInfo storeInfo = storeInfoMapper.getStoreInfo(sellerId);
        storeInfo.setSellerId(sellerId);
        return Result.ok(storeInfo);
    }
}

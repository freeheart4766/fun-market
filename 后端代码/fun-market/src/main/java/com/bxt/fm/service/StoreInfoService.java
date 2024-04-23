package com.bxt.fm.service;

import com.bxt.fm.pojo.StoreInfo;
import com.bxt.fm.util.Result;

public interface StoreInfoService {

    //修改店铺名称
    Result<String> updateStoreName(int sellerId, String storeName);

    //修改店铺头像
    Result<String> updateStoreAvatar(String picturePath, int sellerId);

    //修改店铺封面
    Result<String> updateStoreCover(String picturePath, int sellerId);

    //查询店铺头像和封面的路径
    Result<StoreInfo> getStoreInfo(int sellerId);

    //主页中展示店铺信息
    Result<StoreInfo> getStoreInfoByCustomer(int goodsId);
}

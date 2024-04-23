package com.bxt.fm.mapper;

import com.bxt.fm.pojo.StoreInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("storeInfoMapper")
public interface StoreInfoMapper {

    //修改店铺名称
    int updateStoreName(@Param("sellerId") int sellerId,@Param("storeName") String storeName);

    //修改店铺头像
    void updateStoreAvatar(@Param("picturePath") String picturePath,@Param("sellerId") int sellerId);

    //修改店铺封面
    void updateStoreCover(@Param("picturePath") String picturePath,@Param("sellerId") int sellerId);

    //查询店铺信息
    StoreInfo getStoreInfo(@Param("sellerId") int sellerId);
}

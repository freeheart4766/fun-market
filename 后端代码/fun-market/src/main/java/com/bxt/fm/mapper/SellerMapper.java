package com.bxt.fm.mapper;

import com.bxt.fm.pojo.Seller;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("sellerMapper")
public interface SellerMapper {

    //检查是否有此用户名
    Seller existUsername(@Param("username") String username);

    //登录
    Seller login(@Param("username") String username);

    //注册
    void register(@Param("username") String username,@Param("password") String password);

    //新增店铺信息
    void addStoreInfo(@Param("sellerId") int sellerId, @Param("date") String date);
}

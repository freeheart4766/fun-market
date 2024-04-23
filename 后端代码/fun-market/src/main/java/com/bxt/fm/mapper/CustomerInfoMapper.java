package com.bxt.fm.mapper;

import com.bxt.fm.pojo.CustomerInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("customerInfoMapper")
public interface CustomerInfoMapper {

    //获取用户的昵称
    String getCustomerName(@Param("customerId") int customerId);

    //获取用户的基本信息
    CustomerInfo getCustomerInfo(@Param("customerId") int customerId);

    //保存用户的昵称
    int addCustomerNickname(@Param("customerId") int customerId, @Param("nickname") String nickname);

    //修改用户的基本信息
    int updateCustomerInfo(@Param("customerInfo") CustomerInfo customerInfo);

    //将图片的路径保存到数据库中
    void uploadPicture(@Param("picturePath") String picturePath,@Param("customerId") int customerId);

    //获取头像路径
    String getHeadPicture(@Param("customerId") int customerId);
}

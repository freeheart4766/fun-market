package com.bxt.fm.service;

import com.bxt.fm.pojo.CustomerInfo;
import com.bxt.fm.util.Result;

public interface CustomerInfoService {

    //获取用户的昵称
    Result<String> getCustomerName(int username);

    //获取用户的基本信息
    Result<CustomerInfo> getCustomerInfo(int customerId);

    //修改用户的基本信息
    Result<Integer> updateCustomerInfo(CustomerInfo customerInfo);

    //将图片的路径保存到数据库中
    Result<String> uploadPicture(String picturePath, int customerId);

    //获取头像路径
    String getHeadPicture(int customerId);
}

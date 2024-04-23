package com.bxt.fm.service;

import com.bxt.fm.pojo.ReceivingInfo;
import com.bxt.fm.util.Result;

import java.util.List;

public interface ReceivingInfoService {

    //新增收货地址
    Result<String> addReceivingInfo(ReceivingInfo receivingInfo);

    //查询所有的收货地址信息
    List<ReceivingInfo> getAllReceivingInfo(int customerId);

    //查询单个收货地址信息
    Result<ReceivingInfo> getOneReceivingInfo(int receivingId);

    //删除收货地址信息
    Result<ReceivingInfo> deleteReceivingInfo(int receivingId);

    //修改收货地址
    Result<String> updateReceivingInfo(ReceivingInfo receivingInfo);

    //设为默认地址
    Result<String> setReceivingInfoDefault(int receivingId, int customerId);
}

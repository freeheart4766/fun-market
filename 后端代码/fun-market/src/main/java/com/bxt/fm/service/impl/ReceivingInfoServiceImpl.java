package com.bxt.fm.service.impl;

import com.bxt.fm.mapper.ReceivingInfoMapper;
import com.bxt.fm.pojo.ReceivingInfo;
import com.bxt.fm.service.ReceivingInfoService;
import com.bxt.fm.util.Result;
import com.bxt.fm.util.ResultCodeEnum;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("receivingInfoService")
public class ReceivingInfoServiceImpl implements ReceivingInfoService {
    @Resource(name = "receivingInfoMapper")
    private ReceivingInfoMapper receivingInfoMapper;

    //新增收货地址
    @Override
    public Result<String> addReceivingInfo(ReceivingInfo receivingInfo) {
        if(receivingInfo.getIsDefault().equals("是")){
            String defaultArea = receivingInfoMapper.getDefaultArea(receivingInfo.getCustomerId());
            if (defaultArea != null){
                int defaultReceivingId = receivingInfoMapper.getDefaultReceivingId(receivingInfo.getCustomerId());
                receivingInfoMapper.setReceivingInfoDefault(defaultReceivingId,"否");
                receivingInfoMapper.setReceivingInfoDefault(receivingInfo.getReceivingId(),"是");
                return Result.ok();
            }else {
                return Result.build(null, ResultCodeEnum.ADD_ADDRESS_FAIL);
            }
        }
        int number = receivingInfoMapper.addReceivingInfo(receivingInfo);
        if(number == 1){
            return Result.ok();
        }else {
            return Result.build(null, ResultCodeEnum.ADD_ADDRESS_FAIL);
        }
    }

    //查询所有的收货地址信息
    @Override
    public List<ReceivingInfo> getAllReceivingInfo(int customerId) {
        return receivingInfoMapper.getAllReceivingInfo(customerId);
    }

    //查询单个收货地址信息
    @Override
    public Result<ReceivingInfo> getOneReceivingInfo(int receivingId) {
        ReceivingInfo receivingInfo = receivingInfoMapper.getOneReceivingInfo(receivingId);
        return Result.ok(receivingInfo);
    }

    //删除收货地址信息
    @Override
    public Result<ReceivingInfo> deleteReceivingInfo(int receivingId) {
        int number = receivingInfoMapper.deleteReceivingInfo(receivingId);
        if (number == 1){
            return Result.ok();
        }else {
            return Result.build(null,ResultCodeEnum.DELETE_ADDRESS_FAIL);
        }
    }

    //修改收货地址
    @Override
    public Result<String> updateReceivingInfo(ReceivingInfo receivingInfo) {
        int number = receivingInfoMapper.updateReceivingInfo(receivingInfo);
        if (number == 1){
            return Result.ok();
        }else {
            return Result.build(null,ResultCodeEnum.UPDATE_ADDRESS_FAIL);
        }
    }

    //设为默认地址
    @Override
    public Result<String> setReceivingInfoDefault(int receivingId, int customerId) {
        int defaultAddressId = receivingInfoMapper.getDefaultReceivingId(customerId);
        int numberOld = receivingInfoMapper.setReceivingInfoDefault(defaultAddressId,"否");
        int numberNew = receivingInfoMapper.setReceivingInfoDefault(receivingId,"是");
        if (numberOld == 1 && numberNew ==1){
            return Result.ok();
        }else {
            return Result.build(null,ResultCodeEnum.UPDATE_ADDRESS_FAIL);
        }
    }
}

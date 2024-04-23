package com.bxt.fm.mapper;

import com.bxt.fm.pojo.ReceivingInfo;
import com.bxt.fm.util.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("receivingInfoMapper")
public interface ReceivingInfoMapper {

    //新增收货地址
    int addReceivingInfo(@Param("receivingInfo") ReceivingInfo receivingInfo);

    //查询所有的收货地址信息
    List<ReceivingInfo> getAllReceivingInfo(@Param("customerId") int customerId);

    //查询单个收货地址信息
    ReceivingInfo getOneReceivingInfo(@Param("receivingId") int receivingId);

    //删除收货地址信息
    int deleteReceivingInfo(@Param("receivingId") int receivingId);

    //修改收货地址
    int updateReceivingInfo(@Param("receivingInfo") ReceivingInfo receivingInfo);

    //获取用户默认地址的id
    int getDefaultReceivingId(@Param("customerId") int customerId);

    //修改某个地址信息的isDefault的值
    int setReceivingInfoDefault(@Param("receivingId") int receivingId,@Param("isDefault") String isDefault);

    //获取用户默认地址的area
    String getDefaultArea(@Param("customerId") int customerId);
}

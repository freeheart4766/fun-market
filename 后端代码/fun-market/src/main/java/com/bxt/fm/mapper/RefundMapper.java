package com.bxt.fm.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("refundMapper")
public interface RefundMapper {

    //查询售后未完成的订单数
    int getRefundIncomplete(@Param("customerId") int customerId);
}

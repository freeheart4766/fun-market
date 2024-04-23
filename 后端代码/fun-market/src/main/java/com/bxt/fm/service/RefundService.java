package com.bxt.fm.service;

import com.bxt.fm.util.Result;

public interface RefundService {

    //查询售后未完成的订单数
    Result<Integer> getRefundIncomplete(int customerId);
}

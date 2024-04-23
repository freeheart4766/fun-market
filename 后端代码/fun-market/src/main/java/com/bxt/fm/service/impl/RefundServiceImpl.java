package com.bxt.fm.service.impl;

import com.bxt.fm.mapper.RefundMapper;
import com.bxt.fm.service.RefundService;
import com.bxt.fm.util.Result;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("refundService")
public class RefundServiceImpl implements RefundService {
    @Resource(name = "refundMapper")
    private RefundMapper refundMapper;

    //查询售后未完成的订单数
    @Override
    public Result<Integer> getRefundIncomplete(int customerId) {
        int refundNumber = refundMapper.getRefundIncomplete(customerId);
        return Result.ok(refundNumber);
    }
}

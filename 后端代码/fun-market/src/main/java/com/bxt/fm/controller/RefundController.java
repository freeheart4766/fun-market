package com.bxt.fm.controller;

import com.bxt.fm.service.RefundService;
import com.bxt.fm.util.JwtHelper;
import com.bxt.fm.util.Result;
import com.bxt.fm.util.ResultCodeEnum;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/refund")
public class RefundController {
    @Resource(name = "refundService")
    private RefundService refundService;

    //查询售后未完成的订单数
    @GetMapping("/getRefundIncomplete")
    private Result<Integer> getRefundIncomplete(@RequestHeader("token") String token){
        String signId = JwtHelper.getSignId(token);
        if (signId != null) {
            int customerId = Integer.parseInt(signId);
            return refundService.getRefundIncomplete(customerId);
        }
        return Result.build(null, ResultCodeEnum.LOGIN_AUTH);
    }
}

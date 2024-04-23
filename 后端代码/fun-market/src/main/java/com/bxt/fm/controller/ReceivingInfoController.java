package com.bxt.fm.controller;

import com.bxt.fm.pojo.ReceivingInfo;
import com.bxt.fm.service.ReceivingInfoService;
import com.bxt.fm.util.JwtHelper;
import com.bxt.fm.util.Result;
import com.bxt.fm.util.ResultCodeEnum;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/receivingInfo")
public class ReceivingInfoController {
    @Resource(name = "receivingInfoService")
    private ReceivingInfoService receivingInfoService;

    //新增收货地址
    @PostMapping("/addReceivingInfo")
    public Result<String> addReceivingInfo(
            @RequestHeader("token") String token,
            @RequestBody ReceivingInfo receivingInfo
    ){
        String signId = JwtHelper.getSignId(token);
        if (signId != null) {
            int customerId = Integer.parseInt(signId);
            receivingInfo.setCustomerId(customerId);
            return receivingInfoService.addReceivingInfo(receivingInfo);
        }
        return Result.build(null, ResultCodeEnum.LOGIN_AUTH);
    }

    //查询所有的收货地址信息
    @GetMapping("/getAllReceivingInfo")
    public Result<List<ReceivingInfo>> getAllReceivingInfo(@RequestHeader("token") String token){
        String signId = JwtHelper.getSignId(token);
        if (signId != null) {
            int customerId = Integer.parseInt(signId);
            List<ReceivingInfo> receivingInfos = receivingInfoService.getAllReceivingInfo(customerId);
            return Result.ok(receivingInfos);
        }
        return Result.build(null, ResultCodeEnum.LOGIN_AUTH);
    }

    //修改收货地址
    @PutMapping("/updateReceivingInfo")
    public Result<String> updateReceivingInfo(
            @RequestHeader("token") String token,
            @RequestBody ReceivingInfo receivingInfo
    ){
        String signId = JwtHelper.getSignId(token);
        if (signId != null) {
            return receivingInfoService.updateReceivingInfo(receivingInfo);
        }
        return Result.build(null, ResultCodeEnum.LOGIN_AUTH);
    }

    //查询单个收货地址信息
    @GetMapping("/getOneReceivingInfo/{receivingId}")
    public Result<ReceivingInfo> getOneReceivingInfo(
            @RequestHeader("token") String token,
            @PathVariable("receivingId") int receivingId
    ){
        String signId = JwtHelper.getSignId(token);
        if (signId != null) {
            return receivingInfoService.getOneReceivingInfo(receivingId);
        }
        return Result.build(null, ResultCodeEnum.LOGIN_AUTH);
    }


    //删除收货地址信息
    @DeleteMapping("/deleteReceivingInfo/{receivingId}")
    public Result<ReceivingInfo> deleteReceivingInfo(
            @RequestHeader("token") String token,
            @PathVariable("receivingId") int receivingId
    ){
        String signId = JwtHelper.getSignId(token);
        if (signId != null) {
            return receivingInfoService.deleteReceivingInfo(receivingId);
        }
        return Result.build(null, ResultCodeEnum.LOGIN_AUTH);
    }

    //设为默认地址
    @PutMapping("/setReceivingInfoDefault")
    public Result<String> setReceivingInfoDefault(
            @RequestHeader("token") String token,
            @RequestBody ReceivingInfo receivingInfo
    ){
        String signId = JwtHelper.getSignId(token);
        if (signId != null) {
            int customerId = Integer.parseInt(signId);
            int receivingId = receivingInfo.getReceivingId();
            return receivingInfoService.setReceivingInfoDefault(receivingId,customerId);
        }
        return Result.build(null, ResultCodeEnum.LOGIN_AUTH);
    }
}

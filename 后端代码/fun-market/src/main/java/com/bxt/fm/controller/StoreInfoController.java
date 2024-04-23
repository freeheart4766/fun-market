package com.bxt.fm.controller;

import com.bxt.fm.pojo.StoreInfo;
import com.bxt.fm.service.StoreInfoService;
import com.bxt.fm.util.JwtHelper;
import com.bxt.fm.util.Result;
import com.bxt.fm.util.ResultCodeEnum;
import com.bxt.fm.util.UploadFile;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping("/storeInfo")
public class StoreInfoController {
    @Resource(name = "storeInfoService")
    private StoreInfoService storeInfoService;

    //修改店铺名称
    @PutMapping("/updateStoreName")
    public Result<String> updateStoreName(
            @RequestHeader("token") String token,
            @RequestBody StoreInfo storeInfo
    ){
        String signId = JwtHelper.getSignId(token);
        if (signId != null) {
            int sellerId = Integer.parseInt(signId);
            return storeInfoService.updateStoreName(sellerId,storeInfo.getStoreName());
        }
        return Result.build(null, ResultCodeEnum.LOGIN_AUTH);
    }

    //修改店铺头像
    @PutMapping("/updateStoreAvatar")
    public Result<String> updateStoreAvatar(
            @RequestHeader("token") String token,
            @RequestParam("image") MultipartFile multipartFile
    ){
        String signId = JwtHelper.getSignId(token);
        if (signId != null) {
            int sellerId = Integer.parseInt(signId);
            if (multipartFile == null){
                return Result.build(null,ResultCodeEnum.UPLOAD_FAIL);
            }
            int checkFormat = UploadFile.checkFormat(multipartFile);
            if (checkFormat == 207){
                return Result.build(null,ResultCodeEnum.PICTURE_LARGE);
            }else if (checkFormat == 208){
                return Result.build(null,ResultCodeEnum.PICTURE_TYPE);
            }
            String picturePath = UploadFile.savePicture(multipartFile);
            return storeInfoService.updateStoreAvatar(picturePath,sellerId);
        }
        return Result.build(null, ResultCodeEnum.LOGIN_AUTH);
    }

    //修改店铺封面
    @PutMapping("/updateStoreCover")
    public Result<String> updateStoreCover(
            @RequestHeader("token") String token,
            @RequestParam("image") MultipartFile multipartFile
    ){
        String signId = JwtHelper.getSignId(token);
        if (signId != null) {
            int sellerId = Integer.parseInt(signId);
            if (multipartFile == null){
                return Result.build(null,ResultCodeEnum.UPLOAD_FAIL);
            }
            int checkFormat = UploadFile.checkFormat(multipartFile);
            if (checkFormat == 207){
                return Result.build(null,ResultCodeEnum.PICTURE_LARGE);
            }else if (checkFormat == 208){
                return Result.build(null,ResultCodeEnum.PICTURE_TYPE);
            }
            String picturePath = UploadFile.savePicture(multipartFile);
            return storeInfoService.updateStoreCover(picturePath,sellerId);
        }
        return Result.build(null, ResultCodeEnum.LOGIN_AUTH);
    }

    //查询店铺信息
    @GetMapping("/getStoreInfo")
    public Result<StoreInfo> getStoreInfo(@RequestHeader("token") String token){
        String signId = JwtHelper.getSignId(token);
        if (signId != null) {
            int sellerId = Integer.parseInt(signId);
            return storeInfoService.getStoreInfo(sellerId);
        }
        return Result.build(null, ResultCodeEnum.LOGIN_AUTH);
    }

    //主页中展示店铺信息
    @GetMapping("/getStoreInfoByCustomer/{goodsId}")
    public Result<StoreInfo> getStoreInfoByCustomer(@PathVariable("goodsId") int goodsId){
        return storeInfoService.getStoreInfoByCustomer(goodsId);
    }
}

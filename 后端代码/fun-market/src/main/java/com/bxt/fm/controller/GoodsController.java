package com.bxt.fm.controller;

import com.bxt.fm.pojo.Goods;
import com.bxt.fm.service.GoodsService;
import com.bxt.fm.util.JwtHelper;
import com.bxt.fm.util.Result;
import com.bxt.fm.util.ResultCodeEnum;
import com.bxt.fm.util.UploadFile;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Resource(name = "goodsService")
    private GoodsService goodsService;

    //获取商家的所有商品信息
    @GetMapping("/getAllGoods")
    public Result<List<Goods>> getAllGoods(@RequestHeader("token") String token){
        String signId = JwtHelper.getSignId(token);
        if (signId != null) {
            int sellerId = Integer.parseInt(signId);
            return goodsService.getAllGoods(sellerId);
        }
        return Result.build(null, ResultCodeEnum.LOGIN_AUTH);
    }

    //新增商品信息
    @PostMapping("/addGoods")
    public Result<Integer> addGoods(
            @RequestHeader("token") String token,
            @RequestBody Goods goods
    ){
        String signId = JwtHelper.getSignId(token);
        if (signId != null) {
            int sellerId = Integer.parseInt(signId);
            goods.setSellerId(sellerId);
            return goodsService.addGoods(goods);
        }
        return Result.build(null, ResultCodeEnum.LOGIN_AUTH);
    }

    //修改商品信息
    @PutMapping("/updateGoods")
    public Result<String> updateGoods(
            @RequestHeader("token") String token,
            @RequestBody Goods goods
    ){
        String signId = JwtHelper.getSignId(token);
        if (signId != null) {
            return goodsService.updateGoods(goods);
        }
        return Result.build(null, ResultCodeEnum.LOGIN_AUTH);
    }

    //修改图片信息
    @PutMapping("/updateGoodsPicture")
    public Result<String> updateGoodsPicture(
            @RequestHeader("token") String token,
            @RequestParam("image") MultipartFile multipartFile
    ){
        String signId = JwtHelper.getSignId(token);
        if (signId != null) {
            if (multipartFile == null){
                return Result.build(null,ResultCodeEnum.UPLOAD_FAIL);
            }
            int checkFormat = UploadFile.checkFormat(multipartFile);
            if (checkFormat == 207){
                return Result.build(null,ResultCodeEnum.PICTURE_LARGE);
            }else if (checkFormat == 208){
                return Result.build(null,ResultCodeEnum.PICTURE_TYPE);
            }
            int sellerId = Integer.parseInt(signId);
            String picturePath = UploadFile.savePicture(multipartFile);
            return goodsService.updateGoodsPicture(picturePath,sellerId);
        }
        return Result.build(null, ResultCodeEnum.LOGIN_AUTH);
    }

    //删除商品信息
    @DeleteMapping("/deleteGoods/{goodsId}")
    public Result<String> deleteGoods(
            @RequestHeader("token") String token,
            @PathVariable("goodsId") int goodsId
    ){
        String signId = JwtHelper.getSignId(token);
        if (signId != null) {
            return goodsService.deleteGoods(goodsId);
        }
        return Result.build(null, ResultCodeEnum.LOGIN_AUTH);
    }

    //获取单个商品信息
    @GetMapping("/getGoods/{goodsId}")
    public Result<Goods> getGoods(
            @RequestHeader("token") String token,
            @PathVariable("goodsId") int goodsId
    ){
        String signId = JwtHelper.getSignId(token);
        if (signId != null) {
            return goodsService.getGoods(goodsId);
        }
        return Result.build(null, ResultCodeEnum.LOGIN_AUTH);
    }

    //主页查询销量最高的5个商品
    @GetMapping("/getBestSellingProductsTopFive")
    public Result<List<Goods>> getBestSellingProductsTopFive(){
        return goodsService.getBestSellingProductsTopFive();
    }

    //主页按销量排序查询30个商品
    @GetMapping("/getBestSellingProductsTopThirty")
    public Result<List<Goods>> getBestSellingProductsTopThirty(){
        return goodsService.getBestSellingProductsTopThirty();
    }

    //模糊查询符合名称要求的所有商品信息
    @GetMapping("/getGoodsByName/{goodsName}")
    public Result<List<Goods>> getGoodsByName(@PathVariable("goodsName") String goodsName){
        return goodsService.getGoodsByName(goodsName);
    }

    //查询某类别的所有商品信息
    @GetMapping("/getGoodsByType/{goodsType}")
    public Result<List<Goods>> getGoodsByType(@PathVariable("goodsType") String goodsType){
        return goodsService.getGoodsByType(goodsType);
    }

    //已经登录的顾客查询商品信息
    @GetMapping("/getGoodsByCustomer/{goodsId}")
    public Result<Goods> getGoodsByCustomer(
            @RequestHeader("token") String token,
            @PathVariable("goodsId") int goodsId
    ){
        String signId = JwtHelper.getSignId(token);
        if (signId != null) {
            int customerId = Integer.parseInt(signId);
            return goodsService.getGoodsByCustomer(customerId,goodsId);
        }
        return Result.build(null, ResultCodeEnum.LOGIN_AUTH);
    }

    //未登录的顾客查询商品信息
    @GetMapping("/getGoodsByCustomerWithoutLogin/{goodsId}")
    public Result<Goods> getGoodsByCustomerWithoutLogin(
            @PathVariable("goodsId") int goodsId
    ){
        return goodsService.getGoods(goodsId);
    }

}

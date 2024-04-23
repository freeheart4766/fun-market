package com.bxt.fm.controller;

import com.bxt.fm.pojo.GoodsSingleTypes;
import com.bxt.fm.service.GoodsSingleTypesService;
import com.bxt.fm.util.JwtHelper;
import com.bxt.fm.util.Result;
import com.bxt.fm.util.ResultCodeEnum;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/goodsSingleTypes")
public class GoodsSingleTypesController {
    @Resource(name = "goodsSingleTypesService")
    private GoodsSingleTypesService goodsSingleTypesService;

    //查询所有的商品种类信息
    @GetMapping("/getAllSingleTypes/{goodsId}")
    public Result<List<GoodsSingleTypes>> getAllSingleTypes(
            @RequestHeader("token") String token,
            @PathVariable("goodsId") int goodsId
    ){
        String signId = JwtHelper.getSignId(token);
        if (signId != null) {
            return goodsSingleTypesService.getAllSingleTypes(goodsId);
        }
        return Result.build(null, ResultCodeEnum.LOGIN_AUTH);
    }

    //增加商品种类信息
    @PostMapping("/addSingleTypes")
    public Result<String> addSingleTypes(
            @RequestHeader("token") String token,
            @RequestBody GoodsSingleTypes goodsSingleTypes
    ){
        String signId = JwtHelper.getSignId(token);
        if (signId != null) {
            return goodsSingleTypesService.addSingleTypes(goodsSingleTypes);
        }
        return Result.build(null, ResultCodeEnum.LOGIN_AUTH);
    }

    //修改商品种类信息
    @PutMapping("/updateSingleTypes")
    public Result<String> updateSingleTypes(
            @RequestHeader("token") String token,
            @RequestBody GoodsSingleTypes goodsSingleTypes
    ){
        String signId = JwtHelper.getSignId(token);
        if (signId != null) {
            return goodsSingleTypesService.updateSingleTypes(goodsSingleTypes);
        }
        return Result.build(null, ResultCodeEnum.LOGIN_AUTH);
    }

    //删除商品种类信息
    @DeleteMapping("/deleteSingleTypes/{goodsSingleTypesId}")
    public Result<String> deleteSingleTypes(
            @RequestHeader("token") String token,
            @PathVariable("goodsSingleTypesId") int goodsSingleTypesId
    ){
        String signId = JwtHelper.getSignId(token);
        if (signId != null) {
            return goodsSingleTypesService.deleteSingleTypes(goodsSingleTypesId);
        }
        return Result.build(null, ResultCodeEnum.LOGIN_AUTH);
    }

    //查询单个商品种类信息
    @GetMapping("/getSingleTypes/{goodsSingleTypesId}")
    public Result<GoodsSingleTypes> getSingleTypes(
            @RequestHeader("token") String token,
            @PathVariable("goodsSingleTypesId") int goodsSingleTypesId
    ){
        String signId = JwtHelper.getSignId(token);
        if (signId != null) {
            return goodsSingleTypesService.getSingleTypes(goodsSingleTypesId);
        }
        return Result.build(null, ResultCodeEnum.LOGIN_AUTH);
    }

    //查询商品的最低价格
    @GetMapping("/getMinPrice/{goodsId}")
    public Result<BigDecimal> getMinPrice(@PathVariable("goodsId") int goodsId){
        return goodsSingleTypesService.getMinPrice(goodsId);
    }
}

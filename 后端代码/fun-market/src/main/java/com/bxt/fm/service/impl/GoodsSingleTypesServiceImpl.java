package com.bxt.fm.service.impl;

import com.bxt.fm.mapper.GoodsSingleTypesMapper;
import com.bxt.fm.pojo.GoodsSingleTypes;
import com.bxt.fm.service.GoodsSingleTypesService;
import com.bxt.fm.util.Result;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service("goodsSingleTypesService")
public class GoodsSingleTypesServiceImpl implements GoodsSingleTypesService {
    @Resource(name = "goodsSingleTypesMapper")
    private GoodsSingleTypesMapper goodsSingleTypesMapper;

    //查询所有的商品种类信息
    @Override
    public Result<List<GoodsSingleTypes>> getAllSingleTypes(int goodsId) {
        List<GoodsSingleTypes> goodsSingleTypesList = goodsSingleTypesMapper.getAllSingleTypes(goodsId);
        return Result.ok(goodsSingleTypesList);
    }

    //增加商品种类信息
    @Override
    public Result<String> addSingleTypes(GoodsSingleTypes goodsSingleTypes) {
        goodsSingleTypesMapper.addSingleTypes(goodsSingleTypes);
        return Result.ok();
    }

    //修改商品种类信息
    @Override
    public Result<String> updateSingleTypes(GoodsSingleTypes goodsSingleTypes) {
        goodsSingleTypesMapper.updateSingleTypes(goodsSingleTypes);
        return Result.ok();
    }

    //删除商品种类信息
    @Override
    public Result<String> deleteSingleTypes(int goodsSingleTypesId) {
        goodsSingleTypesMapper.deleteSingleTypes(goodsSingleTypesId);
        return Result.ok();
    }

    //查询单个商品种类信息
    @Override
    public Result<GoodsSingleTypes> getSingleTypes(int goodsSingleTypesId) {
        GoodsSingleTypes goodsSingleTypes = goodsSingleTypesMapper.getSingleTypes(goodsSingleTypesId);
        return Result.ok(goodsSingleTypes);
    }

    //查询商品的最低价格
    @Override
    public Result<BigDecimal> getMinPrice(int goodsId) {
        BigDecimal price = goodsSingleTypesMapper.getMinPrice(goodsId);
        return Result.ok(price);
    }
}

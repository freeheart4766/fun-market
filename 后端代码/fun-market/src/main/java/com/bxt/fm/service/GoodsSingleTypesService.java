package com.bxt.fm.service;

import com.bxt.fm.pojo.GoodsSingleTypes;
import com.bxt.fm.util.Result;

import java.math.BigDecimal;
import java.util.List;

public interface GoodsSingleTypesService {

    //查询所有的商品种类信息
    Result<List<GoodsSingleTypes>> getAllSingleTypes(int goodsId);

    //增加商品种类信息
    Result<String> addSingleTypes(GoodsSingleTypes goodsSingleTypes);

    //修改商品种类信息
    Result<String> updateSingleTypes(GoodsSingleTypes goodsSingleTypes);

    //删除商品种类信息
    Result<String> deleteSingleTypes(int goodsSingleTypesId);

    //查询单个商品种类信息
    Result<GoodsSingleTypes> getSingleTypes(int goodsSingleTypesId);

    //查询商品的最低价格
    Result<BigDecimal> getMinPrice(int goodsId);
}

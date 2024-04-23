package com.bxt.fm.service;

import com.bxt.fm.pojo.Goods;
import com.bxt.fm.util.Result;

import java.util.List;

public interface GoodsService {

    //获取商家的所有商品信息
    Result<List<Goods>> getAllGoods(int sellerId);

    //新增商品信息
    Result<Integer> addGoods(Goods goods);

    //修改商品信息
    Result<String> updateGoods(Goods goods);

    //修改图片信息
    Result<String> updateGoodsPicture(String picturePath, int sellerId);

    //删除商品信息
    Result<String> deleteGoods(int goodsId);

    //获取单个商品信息
    Result<Goods> getGoods(int goodsId);

    //主页查询销量最高的5个商品
    Result<List<Goods>> getBestSellingProductsTopFive();

    //主页按销量排序查询30个商品
    Result<List<Goods>> getBestSellingProductsTopThirty();

    //模糊查询符合名称要求的所有商品信息
    Result<List<Goods>> getGoodsByName(String goodsName);

    //查询某类别的所有商品信息
    Result<List<Goods>> getGoodsByType(String goodsType);

    //已经登录的顾客查询商品信息
    Result<Goods> getGoodsByCustomer(int customerId, int goodsId);
}

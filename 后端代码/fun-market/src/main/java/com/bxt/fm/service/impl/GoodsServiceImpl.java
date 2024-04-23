package com.bxt.fm.service.impl;

import com.bxt.fm.mapper.GoodsMapper;
import com.bxt.fm.mapper.HistoryMapper;
import com.bxt.fm.pojo.Goods;
import com.bxt.fm.pojo.History;
import com.bxt.fm.service.GoodsService;
import com.bxt.fm.util.GetTimeNow;
import com.bxt.fm.util.Result;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
    @Resource(name = "goodsMapper")
    private GoodsMapper goodsMapper;

    @Resource(name = "historyMapper")
    private HistoryMapper historyMapper;

    //获取商家的所有商品信息
    @Override
    public Result<List<Goods>> getAllGoods(int sellerId) {
        List<Goods> list = goodsMapper.getAllGoods(sellerId);
        return Result.ok(list);
    }

    //新增商品信息
    @Override
    public Result<Integer> addGoods(Goods goods) {
        goodsMapper.addGoods(goods);
        int goodsId = goodsMapper.getGoodsId(goods.getSellerId());
        return Result.ok(goodsId);
    }

    //修改商品信息
    @Override
    public Result<String> updateGoods(Goods goods) {
        goodsMapper.updateGoods(goods);
        return Result.ok();
    }

    //修改图片信息
    @Override
    public Result<String> updateGoodsPicture(String picturePath, int sellerId) {
        int goodsId = goodsMapper.getGoodsId(sellerId);
        goodsMapper.updateGoodsPicture(picturePath,goodsId);
        return Result.ok();
    }

    //删除商品信息
    @Override
    public Result<String> deleteGoods(int goodsId) {
        goodsMapper.deleteGoods(goodsId);
        return Result.ok();
    }

    //获取单个商品信息
    @Override
    public Result<Goods> getGoods(int goodsId) {
        Goods goods = goodsMapper.getGoods(goodsId);
        return Result.ok(goods);
    }

    //主页查询销量最高的5个商品
    @Override
    public Result<List<Goods>> getBestSellingProductsTopFive() {
        List<Goods> list = goodsMapper.getBestSellingProductsTopFive();
        return Result.ok(list);
    }

    //主页按销量排序查询30个商品
    @Override
    public Result<List<Goods>> getBestSellingProductsTopThirty() {
        List<Goods> list = goodsMapper.getBestSellingProductsTopThirty();
        return Result.ok(list);
    }

    //模糊查询符合名称要求的所有商品信息
    @Override
    public Result<List<Goods>> getGoodsByName(String goodsName) {
        List<Goods> list = goodsMapper.getGoodsByName(goodsName);
        return Result.ok(list);
    }

    //查询某类别的所有商品信息
    @Override
    public Result<List<Goods>> getGoodsByType(String goodsType) {
        List<Goods> list = goodsMapper.getGoodsByType(goodsType);
        return Result.ok(list);
    }

    //已经登录的顾客查询商品信息
    @Override
    public Result<Goods> getGoodsByCustomer(int customerId, int goodsId) {
        //将此顾客查看此商品的信息添加进历史记录表
        String date = GetTimeNow.getDate();
        History history = new History();
        history.setCustomerId(customerId);
        history.setGoodsId(goodsId);
        history.setTime(date);
        historyMapper.addHistory(history);
        //查询此goodsId的商品信息
        Goods goods = goodsMapper.getGoods(goodsId);
        return Result.ok(goods);
    }
}

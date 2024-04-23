package com.bxt.fm.mapper;

import com.bxt.fm.pojo.Goods;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("goodsMapper")
public interface GoodsMapper {

    //获取商家的所有商品信息
    List<Goods> getAllGoods(@Param("sellerId") int sellerId);

    //新增商品信息
    void addGoods(@Param("goods") Goods goods);

    //修改商品信息
    void updateGoods(@Param("goods") Goods goods);

    //修改图片信息
    void updateGoodsPicture(@Param("picturePath") String picturePath,@Param("goodsId") int goodsId);

    //删除商品信息
    void deleteGoods(@Param("goodsId") int goodsId);

    //获取单个商品信息
    Goods getGoods(@Param("goodsId") int goodsId);

    //查询刚刚新增商品的goods_id
    int getGoodsId(@Param("sellerId") int sellerId);

    //主页查询销量最高的5个商品
    List<Goods> getBestSellingProductsTopFive();

    //主页按销量排序查询30个商品
    List<Goods> getBestSellingProductsTopThirty();

    //模糊查询符合名称要求的所有商品信息
    List<Goods> getGoodsByName(@Param("goodsName") String goodsName);

    //查询某类别的所有商品信息
    List<Goods> getGoodsByType(@Param("goodsType") String goodsType);

    //根据商品id查询商家id
    int getGoodsIdBySellerId(@Param("goodsId") int goodsId);
}

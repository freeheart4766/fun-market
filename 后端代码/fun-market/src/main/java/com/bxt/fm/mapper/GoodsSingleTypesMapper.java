package com.bxt.fm.mapper;

import com.bxt.fm.pojo.GoodsSingleTypes;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository("goodsSingleTypesMapper")
public interface GoodsSingleTypesMapper {

    //查询所有的商品种类信息
    List<GoodsSingleTypes> getAllSingleTypes(@Param("goodsId") int goodsId);

    //增加商品种类信息
    void addSingleTypes(@Param("goodsSingleTypes") GoodsSingleTypes goodsSingleTypes);

    //修改商品种类信息
    void updateSingleTypes(@Param("goodsSingleTypes") GoodsSingleTypes goodsSingleTypes);

    //删除商品种类信息
    void deleteSingleTypes(@Param("goodsSingleTypesId") int goodsSingleTypesId);

    //查询单个商品种类信息
    GoodsSingleTypes getSingleTypes(@Param("goodsSingleTypesId") int goodsSingleTypesId);

    //查询商品的最低价格
    BigDecimal getMinPrice(@Param("goodsId") int goodsId);
}

package com.bxt.fm.mapper;

import com.bxt.fm.pojo.History;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("historyMapper")
public interface HistoryMapper {

    //将此顾客查看此商品的信息添加进历史记录表
    void addHistory(@Param("history") History history);
}

package com.bxt.fm.mapper;

import com.bxt.fm.pojo.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("orderMapper")
public interface OrderMapper {

    //获取某种状态的订单个数
    int getOrderStatus(@Param("customerId") int customerId, @Param("orderStatus") String orderStatus);

    //新增订单
    void addOrder(@Param("order") Order order);
}

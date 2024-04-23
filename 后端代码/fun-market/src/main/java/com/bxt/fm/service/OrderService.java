package com.bxt.fm.service;

import com.bxt.fm.pojo.Order;
import com.bxt.fm.pojo.OrderStatus;
import com.bxt.fm.util.Result;

public interface OrderService {

    //获取订单各种状态的个数
    Result<OrderStatus> getOrderStatus(int customerId);

    //新增订单
    Result<String> addOrder(Order order);
}

package com.bxt.fm.service.impl;

import com.bxt.fm.mapper.OrderMapper;
import com.bxt.fm.pojo.Order;
import com.bxt.fm.pojo.OrderStatus;
import com.bxt.fm.service.OrderService;
import com.bxt.fm.util.Result;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Resource(name = "orderMapper")
    private OrderMapper orderMapper;

    //获取订单各种状态的个数
    @Override
    public Result<OrderStatus> getOrderStatus(int customerId) {
        int awaitingPayment = orderMapper.getOrderStatus(customerId,"待付款");
        int awaitingShipment = orderMapper.getOrderStatus(customerId,"待发货");
        int awaitingDelivery = orderMapper.getOrderStatus(customerId,"待收货");
        int awaitingEvaluation = orderMapper.getOrderStatus(customerId,"待评价");
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setAwaitingPayment(awaitingPayment);
        orderStatus.setAwaitingShipment(awaitingShipment);
        orderStatus.setAwaitingDelivery(awaitingDelivery);
        orderStatus.setAwaitingEvaluation(awaitingEvaluation);
        return Result.ok(orderStatus);
    }

    //新增订单
    @Override
    public Result<String> addOrder(Order order) {
        orderMapper.addOrder(order);
        return Result.ok();
    }
}

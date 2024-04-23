package com.bxt.fm.controller;

import com.bxt.fm.pojo.Order;
import com.bxt.fm.pojo.OrderStatus;
import com.bxt.fm.service.OrderService;
import com.bxt.fm.util.GetTimeNow;
import com.bxt.fm.util.JwtHelper;
import com.bxt.fm.util.Result;
import com.bxt.fm.util.ResultCodeEnum;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource(name = "orderService")
    private OrderService orderService;

    //获取订单各种状态的个数
    @GetMapping("/getOrderStatus")
    public Result<OrderStatus> getOrderStatus(@RequestHeader("token") String token){
        String signId = JwtHelper.getSignId(token);
        if (signId != null) {
            int customerId = Integer.parseInt(signId);
            return orderService.getOrderStatus(customerId);
        }
        return Result.build(null, ResultCodeEnum.LOGIN_AUTH);
    }

    //新增订单
    @PostMapping("/addOrder")
    public Result<String> addOrder(
            @RequestHeader("token") String token,
            @RequestBody Order order
    ){
        String signId = JwtHelper.getSignId(token);
        if (signId != null) {
            int customerId = Integer.parseInt(signId);
            order.setCustomerId(customerId);
            String time = GetTimeNow.getTime();
            order.setOrderDate(time);
            order.setOrderStatus("待发货");
            return orderService.addOrder(order);
        }
        return Result.build(null, ResultCodeEnum.LOGIN_AUTH);
    }

    //查询顾客的所有订单
    //查询商家的所有订单
}

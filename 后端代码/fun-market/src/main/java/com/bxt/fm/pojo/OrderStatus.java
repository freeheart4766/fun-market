package com.bxt.fm.pojo;

//存放各种状态订单的个数

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatus {

    private Integer awaitingPayment;    //待付款
    private Integer awaitingShipment;    //待发货
    private Integer awaitingDelivery;    //待收货
    private Integer awaitingEvaluation;    //待评价
}

package com.bxt.fm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Integer orderId;
    private Integer goodsId;
    private Integer customerId;
    private Integer receivingId;
    private String orderDate;
    private Integer number;
    private String goodsName;
    private String goodsPicturePath;
    private String goodsSingleTypeName;
    private BigDecimal goodsPrice;
    private BigDecimal pay;
    private String orderStatus;
}

package com.bxt.fm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Refund {

    private Integer refundId;
    private Integer orderId;
    private Integer customerId;
    private String time;
    private String service;
    private String status;
    private String reason;
    private String details;
}

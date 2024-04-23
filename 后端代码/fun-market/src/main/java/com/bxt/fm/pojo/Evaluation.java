package com.bxt.fm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Evaluation {

    private Integer evaluationId;
    private Integer customerId;
    private Integer goodsId;
    private String date;
    private String evaluationLevel;
    private Integer productDescription;
    private Integer sellerService;
    private Integer logisticsService;
    private String evaluationContent;
}

package com.bxt.fm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsSingleTypes {

    private Integer goodsSingleTypesId;
    private Integer goodsId;
    private String typesName;
    private BigDecimal goodsPrice;
}

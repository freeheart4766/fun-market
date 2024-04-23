package com.bxt.fm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCart {

    private Integer shoppingCartId;
    private Integer customerId;
    private Integer goodsId;
    private Integer goodsSingleTypesId;
    private Integer number;
}

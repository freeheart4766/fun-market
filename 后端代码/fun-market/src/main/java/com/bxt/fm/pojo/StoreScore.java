package com.bxt.fm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreScore {

    private Integer sellerId;
    private Double productDescription;
    private Double sellerService;
    private Double logisticsService;
}

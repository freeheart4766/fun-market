package com.bxt.fm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {

    private Integer goodsId;
    private Integer sellerId;
    private String goodsName;
    private String goodsType;
    private Integer goodsInventory;
    private Integer salesVolume;
    private String goodsCoverPath;
}

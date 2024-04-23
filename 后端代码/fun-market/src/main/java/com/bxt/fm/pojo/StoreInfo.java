package com.bxt.fm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreInfo {

    private Integer sellerId;
    private String storeName;
    private String openingDate;
    private String portraitPath;
    private String storeCoverPicture;
}

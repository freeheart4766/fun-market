package com.bxt.fm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceivingInfo {

    private Integer receivingId;
    private Integer customerId;
    private String receiverName;
    private String area;
    private String detailAddress;
    private String telephone;
    private String isDefault;
}

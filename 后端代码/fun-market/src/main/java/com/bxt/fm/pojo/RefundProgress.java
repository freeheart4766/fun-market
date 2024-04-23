package com.bxt.fm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefundProgress {

    private Integer refundId;
    private String stepOneTime;
    private String stepTwoTime;
    private String stepThreeTime;
    private String stepFourTime;
    private String stepFiveTime;
    private String stepSixTime;
}

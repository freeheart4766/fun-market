package com.bxt.fm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateNow {

    private Integer year;
    private Integer month;
    private String week;
    private Integer day;
}

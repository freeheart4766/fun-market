package com.bxt.fm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerInfo {

    private Integer customerId;
    private String nickname;
    private String gender;
    private String birthday;
    private String description;
    private String telephone;
    private String email;
    private String portraitPath;

}

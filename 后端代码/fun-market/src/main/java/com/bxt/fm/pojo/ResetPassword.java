package com.bxt.fm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetPassword {

    private String verifyCode;
    private String encryptionVerifyCode;
    private String passwordNew;
}

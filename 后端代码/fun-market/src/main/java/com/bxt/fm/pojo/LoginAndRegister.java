package com.bxt.fm.pojo;

//存放登录或注册时的用户名、密码和验证码

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginAndRegister {

    private String username;
    private String password;
    private String verifyCode;
}

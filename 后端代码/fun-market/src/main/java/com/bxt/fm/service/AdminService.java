package com.bxt.fm.service;

import com.bxt.fm.pojo.Admin;
import com.bxt.fm.util.Result;

public interface AdminService {

    //检查是否有此用户名
    Result<Admin> existUsername(String username);

    //登录
    Result<String> login(Admin admin, String verifyCode, String verifyCodeBefore);

    //注册之前，检查用户名是否已占用
    Result<Admin> usernameOccupy(String username);

    //注册
    Result<Admin> register(Admin admin, String verifyCode, String verifyCodeBefore);
}

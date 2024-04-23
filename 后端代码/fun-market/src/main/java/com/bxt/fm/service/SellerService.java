package com.bxt.fm.service;

import com.bxt.fm.pojo.Seller;
import com.bxt.fm.util.Result;

public interface SellerService {

    //检查是否有此用户名
    Result<Seller> existUsername(String username);

    //登录
    Result<String> login(Seller seller, String verifyCode, String verifyCodeBefore);

    //注册之前，检查用户名是否已占用
    Result<Seller> usernameOccupy(String username);

    //注册
    Result<Seller> register(Seller seller, String verifyCode, String verifyCodeBefore);
}

package com.bxt.fm.service;

import com.bxt.fm.pojo.Authentication;
import com.bxt.fm.util.Result;

public interface AuthenticationService {

    //获取是否实名的信息
    Result<Integer> getAuthenticationStatus(int customerId);

    //保存实名信息
    Result<String> addAuthentication(Authentication authentication);
}

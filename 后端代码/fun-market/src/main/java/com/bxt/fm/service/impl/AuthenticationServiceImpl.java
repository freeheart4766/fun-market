package com.bxt.fm.service.impl;

import com.bxt.fm.mapper.AuthenticationMapper;
import com.bxt.fm.pojo.Authentication;
import com.bxt.fm.service.AuthenticationService;
import com.bxt.fm.util.Result;
import com.bxt.fm.util.ResultCodeEnum;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService {

    @Resource(name = "authenticationMapper")
    private AuthenticationMapper authenticationMapper;

    //获取是否实名的信息
    @Override
    public Result<Integer> getAuthenticationStatus(int customerId) {
        String trueName = authenticationMapper.getAuthenticationStatus(customerId);
        if (trueName == null){
            return Result.ok(0);
        }else {
            return Result.ok(1);
        }
    }

    //保存实名信息
    @Override
    public Result<String> addAuthentication(Authentication authentication) {
        int number = authenticationMapper.addAuthentication(authentication);
        if (number == 1){
            return Result.ok();
        }else {
            return Result.build(null, ResultCodeEnum.AUTHENTICATION_FAIL);
        }
    }
}

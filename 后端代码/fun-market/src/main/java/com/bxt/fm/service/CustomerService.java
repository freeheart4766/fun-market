package com.bxt.fm.service;

import com.bxt.fm.pojo.Customer;
import com.bxt.fm.util.Result;

public interface CustomerService {

    //检查是否有此用户名
    Result<Customer> existUsername(String username);

    //登录，检查用户名，密码和验证码是否正确
    Result<String> login(Customer customer, String verifyCode, String verifyCodeBefore);

    //注册
    Result<Customer> register(Customer customer, String verifyCode, String verifyCodeBefore);

    //注册之前，检查用户名是否已占用
    Result<Customer> usernameOccupy(String username);

    //修改密码
    Result<String> changePassword(int customerId, String passwordNew);

    //修改支付密码
    Result<String> changePayPassword(int customerId, String passwordNew);
}

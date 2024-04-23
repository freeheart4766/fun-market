package com.bxt.fm.service.impl;

import com.bxt.fm.mapper.CustomerMapper;
import com.bxt.fm.pojo.Customer;
import com.bxt.fm.service.CustomerService;
import com.bxt.fm.util.JwtHelper;
import com.bxt.fm.util.MD5;
import com.bxt.fm.util.Result;
import com.bxt.fm.util.ResultCodeEnum;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
    @Resource(name = "customerMapper")
    private CustomerMapper customerMapper;

    //检查是否有此用户名
    @Override
    public Result<Customer> existUsername(String username) {
        Customer customer = customerMapper.existUsername(username);
        if (customer == null){
            return Result.build(null, ResultCodeEnum.USERNAME_NONE);
        }else {
            return Result.ok(customer);
        }
    }

    //登录，检查用户名，密码和验证码是否正确
    @Override
    public Result<String> login(Customer customer, String verifyCode, String verifyCodeBefore) {
        //调用mapper层的方法，但只传入用户名
        Customer customerNew = customerMapper.login(customer.getCustomerUsername());
        //判断密码是否正确
        if (!MD5.encrypt(customer.getCustomerPassword()).equals(customerNew.getCustomerPassword())){
            return Result.build(null, ResultCodeEnum.LOGIN_ERROR);
        }
        //判断验证码与之前的验证码是否相同
        if (!verifyCode.equalsIgnoreCase(verifyCodeBefore)){
            return Result.build(null,ResultCodeEnum.CODE_ERROR);
        }
        //全部符合后，返回customerId
        String token = JwtHelper.createToken(String.valueOf(customerNew.getCustomerId()));
        return Result.ok(token);
    }

    //注册之前，检查用户名是否已占用
    @Override
    public Result<Customer> usernameOccupy(String username) {
        Customer customer = customerMapper.existUsername(username);
        if (customer != null){
            return Result.build(null,ResultCodeEnum.USERNAME_OCCUPY);
        }
        return Result.ok();
    }

    //注册
    @Override
    public Result<Customer> register(Customer customer, String verifyCode, String verifyCodeBefore) {
        //判断验证码与之前产生的验证码是否相同
        if (!verifyCodeBefore.equalsIgnoreCase(verifyCode)){
            return Result.build(null,ResultCodeEnum.CODE_ERROR);
        }else {
            customerMapper.register(customer.getCustomerUsername(),MD5.encrypt(customer.getCustomerPassword()));
            return Result.ok();
        }
    }

    //修改密码
    @Override
    public Result<String> changePassword(int customerId, String passwordNew) {
        int number = customerMapper.changePassword(customerId,MD5.encrypt(passwordNew));
        if (number != 1){
            return Result.build(null,ResultCodeEnum.PASSWORD_CHANGE_ERROR);
        }else {
            return Result.ok();
        }
    }

    //修改支付密码
    @Override
    public Result<String> changePayPassword(int customerId, String passwordNew) {
        int number = customerMapper.changePayPassword(customerId,MD5.encrypt(passwordNew));
        if (number != 1){
            return Result.build(null,ResultCodeEnum.PASSWORD_CHANGE_ERROR);
        }else {
            return Result.ok();
        }
    }

}

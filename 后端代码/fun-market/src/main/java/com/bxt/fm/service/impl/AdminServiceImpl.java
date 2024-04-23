package com.bxt.fm.service.impl;

import com.bxt.fm.mapper.AdminMapper;
import com.bxt.fm.pojo.Admin;
import com.bxt.fm.service.AdminService;
import com.bxt.fm.util.JwtHelper;
import com.bxt.fm.util.MD5;
import com.bxt.fm.util.Result;
import com.bxt.fm.util.ResultCodeEnum;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Resource(name = "adminMapper")
    private AdminMapper adminMapper;

    //检查是否有此用户名
    @Override
    public Result<Admin> existUsername(String username) {
        Admin admin = adminMapper.existUsername(username);
        if (admin == null){
            return Result.build(null, ResultCodeEnum.USERNAME_NONE);
        }else {
            return Result.ok(admin);
        }
    }

    //登录
    @Override
    public Result<String> login(Admin admin, String verifyCode, String verifyCodeBefore) {
        //判断验证码与之前的验证码是否相同
        if (!verifyCode.equalsIgnoreCase(verifyCodeBefore)){
            return Result.build(null,ResultCodeEnum.CODE_ERROR);
        }
        //调用mapper层的方法，但只传入用户名
        Admin adminNew = adminMapper.login(admin.getAdminUsername());
        //判断密码是否正确
        if (!MD5.encrypt(admin.getAdminPassword()).equals(adminNew.getAdminPassword())){
            return Result.build(null, ResultCodeEnum.LOGIN_ERROR);
        }
        //全部符合后，返回sellerId
        String token = JwtHelper.createToken(String.valueOf(adminNew.getAdminId()));
        return Result.ok(token);
    }

    //注册之前，检查用户名是否已占用
    @Override
    public Result<Admin> usernameOccupy(String username) {
        Admin admin = adminMapper.existUsername(username);
        if (admin != null){
            return Result.build(null,ResultCodeEnum.USERNAME_OCCUPY);
        }
        return Result.ok();
    }

    //注册
    @Override
    public Result<Admin> register(Admin admin, String verifyCode, String verifyCodeBefore) {
        //判断验证码与之前产生的验证码是否相同
        if (!verifyCodeBefore.equalsIgnoreCase(verifyCode)){
            return Result.build(null,ResultCodeEnum.CODE_ERROR);
        }else {
            adminMapper.register(admin.getAdminUsername(),MD5.encrypt(admin.getAdminPassword()));
            return Result.ok();
        }
    }
}

package com.bxt.fm.service.impl;

import com.bxt.fm.mapper.SellerMapper;
import com.bxt.fm.pojo.Seller;
import com.bxt.fm.service.SellerService;
import com.bxt.fm.util.*;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("sellerService")
public class SellerServiceImpl implements SellerService {

    @Resource(name = "sellerMapper")
    private SellerMapper sellerMapper;

    //检查是否有此用户名
    @Override
    public Result<Seller> existUsername(String username) {
        Seller seller = sellerMapper.existUsername(username);
        if (seller == null){
            return Result.build(null, ResultCodeEnum.USERNAME_NONE);
        }else {
            return Result.ok(seller);
        }
    }

    //登录
    @Override
    public Result<String> login(Seller seller, String verifyCode, String verifyCodeBefore) {
        //判断验证码与之前的验证码是否相同
        if (!verifyCode.equalsIgnoreCase(verifyCodeBefore)){
            return Result.build(null,ResultCodeEnum.CODE_ERROR);
        }
        //调用mapper层的方法，但只传入用户名
        Seller sellerNew = sellerMapper.login(seller.getSellerUsername());
        //判断密码是否正确
        if (!MD5.encrypt(seller.getSellerPassword()).equals(sellerNew.getSellerPassword())){
            return Result.build(null, ResultCodeEnum.LOGIN_ERROR);
        }
        //全部符合后，返回sellerId
        String token = JwtHelper.createToken(String.valueOf(sellerNew.getSellerId()));
        return Result.ok(token);
    }

    //注册之前，检查用户名是否已占用
    @Override
    public Result<Seller> usernameOccupy(String username) {
        Seller seller = sellerMapper.existUsername(username);
        if (seller != null){
            return Result.build(null,ResultCodeEnum.USERNAME_OCCUPY);
        }
        return Result.ok();
    }

    //注册
    @Override
    public Result<Seller> register(Seller seller, String verifyCode, String verifyCodeBefore) {
        //判断验证码与之前产生的验证码是否相同
        if (!verifyCodeBefore.equalsIgnoreCase(verifyCode)){
            return Result.build(null,ResultCodeEnum.CODE_ERROR);
        }else {
            sellerMapper.register(seller.getSellerUsername(),MD5.encrypt(seller.getSellerPassword()));
            //在店铺表中新增一条记录
            Seller sellerNew = sellerMapper.login(seller.getSellerUsername());
            //获取seller的id
            int sellerId = sellerNew.getSellerId();
            //获取当前的日期
            String date = GetTimeNow.getDate();
            sellerMapper.addStoreInfo(sellerId,date);
            return Result.ok();
        }
    }
}

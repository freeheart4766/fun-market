package com.bxt.fm.controller;

import com.bxt.fm.pojo.LoginAndRegister;
import com.bxt.fm.pojo.Seller;
import com.bxt.fm.service.SellerService;
import com.bxt.fm.util.JwtHelper;
import com.bxt.fm.util.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/seller")
public class SellerController {

    @Resource(name = "sellerService")
    private SellerService sellerService;

    //检查是否有此用户名
    @GetMapping("/existUsername/{username}")
    public Result<Seller> existUsername(@PathVariable("username") String username){
        return sellerService.existUsername(username);
    }

    //登录，检查用户名，密码和验证码是否正确
    @PostMapping("/login")
    public Result<String> login(
            @RequestBody LoginAndRegister loginAndRegister,
            @RequestHeader("verify") String verifyCodeEncryption
    ){
        Seller seller = new Seller();
        seller.setSellerUsername(loginAndRegister.getUsername());
        seller.setSellerPassword(loginAndRegister.getPassword());
        String verifyCode = loginAndRegister.getVerifyCode();
        String verifyCodeBefore = JwtHelper.getSignId(verifyCodeEncryption);
        return sellerService.login(seller,verifyCode,verifyCodeBefore);
    }

    //注册之前，检查用户名是否已占用
    @GetMapping("/usernameOccupy/{username}")
    public Result<Seller> usernameOccupy(@PathVariable("username") String username){
        return sellerService.usernameOccupy(username);
    }

    //注册
    @PostMapping("/register")
    public Result<Seller> register(
            @RequestBody LoginAndRegister loginAndRegister,
            @RequestHeader("verify") String verifyCodeEncryption
    ){
        Seller seller = new Seller();
        seller.setSellerUsername(loginAndRegister.getUsername());
        seller.setSellerPassword(loginAndRegister.getPassword());
        String verifyCode = loginAndRegister.getVerifyCode();
        String verifyCodeBefore = JwtHelper.getSignId(verifyCodeEncryption);
        return sellerService.register(seller,verifyCode,verifyCodeBefore);
    }
}

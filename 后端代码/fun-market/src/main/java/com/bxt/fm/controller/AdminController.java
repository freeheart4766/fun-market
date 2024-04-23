package com.bxt.fm.controller;

import com.bxt.fm.pojo.Admin;
import com.bxt.fm.pojo.LoginAndRegister;
import com.bxt.fm.service.AdminService;
import com.bxt.fm.util.JwtHelper;
import com.bxt.fm.util.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource(name = "adminService")
    private AdminService adminService;

    //检查是否有此用户名
    @GetMapping("/existUsername/{username}")
    public Result<Admin> existUsername(@PathVariable("username") String username){
        return adminService.existUsername(username);
    }

    //登录，检查用户名，密码和验证码是否正确
    @PostMapping("/login")
    public Result<String> login(
            @RequestBody LoginAndRegister loginAndRegister,
            @RequestHeader("verify") String verifyCodeEncryption
    ){
        Admin admin = new Admin();
        admin.setAdminUsername(loginAndRegister.getUsername());
        admin.setAdminPassword(loginAndRegister.getPassword());
        String verifyCode = loginAndRegister.getVerifyCode();
        String verifyCodeBefore = JwtHelper.getSignId(verifyCodeEncryption);
        return adminService.login(admin,verifyCode,verifyCodeBefore);
    }

    //注册之前，检查用户名是否已占用
    @GetMapping("/usernameOccupy/{username}")
    public Result<Admin> usernameOccupy(@PathVariable("username") String username){
        return adminService.usernameOccupy(username);
    }

    //注册
    @PostMapping("/register")
    public Result<Admin> register(
            @RequestBody LoginAndRegister loginAndRegister,
            @RequestHeader("verify") String verifyCodeEncryption
    ){
        Admin admin = new Admin();
        admin.setAdminUsername(loginAndRegister.getUsername());
        admin.setAdminPassword(loginAndRegister.getPassword());
        String verifyCode = loginAndRegister.getVerifyCode();
        String verifyCodeBefore = JwtHelper.getSignId(verifyCodeEncryption);
        return adminService.register(admin,verifyCode,verifyCodeBefore);
    }
}

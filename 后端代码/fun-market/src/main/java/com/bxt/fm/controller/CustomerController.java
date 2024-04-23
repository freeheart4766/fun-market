package com.bxt.fm.controller;

import com.bxt.fm.pojo.Customer;
import com.bxt.fm.pojo.LoginAndRegister;
import com.bxt.fm.pojo.ResetPassword;
import com.bxt.fm.service.CustomerService;
import com.bxt.fm.util.JwtHelper;
import com.bxt.fm.util.Result;
import com.bxt.fm.util.ResultCodeEnum;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@CrossOrigin   //允许跨源访问服务器资源
@RestController    //自动将返回结果转成json格式
@RequestMapping("/customer")
public class CustomerController {
    @Resource(name = "customerService")
    private CustomerService customerService;

    //检查是否有此用户名
    @GetMapping ("/existUsername/{username}")
    public Result<Customer> existUsername(@PathVariable("username") String username){
        return customerService.existUsername(username);
    }

    //登录，检查用户名，密码和验证码是否正确
    @PostMapping("/login")
    public Result<String> login(
            @RequestBody LoginAndRegister loginAndRegister,
            @RequestHeader("verify") String verifyCodeEncryption
    ){
        Customer customer = new Customer();
        customer.setCustomerUsername(loginAndRegister.getUsername());
        customer.setCustomerPassword(loginAndRegister.getPassword());
        String verifyCode = loginAndRegister.getVerifyCode();
        String verifyCodeBefore = JwtHelper.getSignId(verifyCodeEncryption);
        return customerService.login(customer,verifyCode,verifyCodeBefore);
    }

    //注册之前，检查用户名是否已占用
    @GetMapping("/usernameOccupy/{username}")
    public Result<Customer> usernameOccupy(@PathVariable("username") String username){
        return customerService.usernameOccupy(username);
    }

    //注册
    @PostMapping("/register")
    public Result<Customer> register(
            @RequestBody LoginAndRegister loginAndRegister,
            @RequestHeader("verify") String verifyCodeEncryption
    ){
        Customer customer = new Customer();
        customer.setCustomerUsername(loginAndRegister.getUsername());
        customer.setCustomerPassword(loginAndRegister.getPassword());
        String verifyCode = loginAndRegister.getVerifyCode();
        String verifyCodeBefore = JwtHelper.getSignId(verifyCodeEncryption);
        return customerService.register(customer,verifyCode,verifyCodeBefore);
    }

    //修改密码
    @PutMapping("/changePassword")
    public Result<String> changePassword(
            @RequestHeader("token") String token,
            @RequestBody ResetPassword resetPassword
    ){
        String signId = JwtHelper.getSignId(token);
        if (signId != null) {
            int customerId = Integer.parseInt(signId);
            String encryptionVerifyCode = resetPassword.getEncryptionVerifyCode();
            String verifyBefore = JwtHelper.getSignId(encryptionVerifyCode);
            if (!resetPassword.getVerifyCode().equals(verifyBefore)){
                return Result.build(null,ResultCodeEnum.CODE_ERROR);
            }
            return customerService.changePassword(customerId,resetPassword.getPasswordNew());
        }
        return Result.build(null, ResultCodeEnum.LOGIN_AUTH);
    }

    //修改支付密码
    @PutMapping("/changePayPassword")
    public Result<String> changePayPassword(
            @RequestHeader("token") String token,
            @RequestBody ResetPassword resetPassword
    ){
        String signId = JwtHelper.getSignId(token);
        if (signId != null) {
            int customerId = Integer.parseInt(signId);
            String encryptionVerifyCode = resetPassword.getEncryptionVerifyCode();
            String verifyBefore = JwtHelper.getSignId(encryptionVerifyCode);
            if (!resetPassword.getVerifyCode().equals(verifyBefore)){
                return Result.build(null,ResultCodeEnum.CODE_ERROR);
            }
            return customerService.changePayPassword(customerId,resetPassword.getPasswordNew());
        }
        return Result.build(null, ResultCodeEnum.LOGIN_AUTH);
    }
}

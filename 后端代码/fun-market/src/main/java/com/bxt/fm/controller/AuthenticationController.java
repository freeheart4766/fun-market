package com.bxt.fm.controller;

import com.bxt.fm.pojo.Authentication;
import com.bxt.fm.service.AuthenticationService;
import com.bxt.fm.util.JwtHelper;
import com.bxt.fm.util.Result;
import com.bxt.fm.util.ResultCodeEnum;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @Resource(name = "authenticationService")
    private AuthenticationService authenticationService;

    //获取是否实名的信息
    @GetMapping("/getAuthenticationStatus")
    public Result<Integer> getAuthenticationStatus(@RequestHeader("token") String token){
        String signId = JwtHelper.getSignId(token);
        if (signId != null) {
            int customerId = Integer.parseInt(signId);
            return authenticationService.getAuthenticationStatus(customerId);
        }
        return Result.build(null, ResultCodeEnum.LOGIN_AUTH);
    }

    //保存实名信息
    @PostMapping("/addAuthentication")
    public Result<String> addAuthentication(
            @RequestHeader("token") String token,
            @RequestBody Authentication authentication
    ){
        String signId = JwtHelper.getSignId(token);
        if (signId != null) {
            int customerId = Integer.parseInt(signId);
            authentication.setCustomerId(customerId);
            return authenticationService.addAuthentication(authentication);
        }
        return Result.build(null, ResultCodeEnum.LOGIN_AUTH);
    }

}

package com.bxt.fm.controller;

//与数据库无关的公共的操作

import com.bxt.fm.pojo.DateNow;
import com.bxt.fm.util.*;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@CrossOrigin
@RestController
@RequestMapping("/system")
public class SystemController {

    //获取验证码图片
    @CrossOrigin(origins = "http://localhost:5173/",allowedHeaders = "verify",allowCredentials = "true",exposedHeaders = "verify")
    @GetMapping("/getVerifyCodeImage")
    public void getVerifyCodeImage(HttpServletResponse response){
        //获取图片
        BufferedImage verifyCodeImage = CreateVerifyCodeImage.getVerifyCodeImage();
        //获取图片上的验证码
        char[] verifyCode = CreateVerifyCodeImage.getVerifyCode();
        //将验证码放入token中，在响应头中传给客户端，为之后的验证做准备
        String token = JwtHelper.createToken(String.valueOf(verifyCode));
        response.setHeader("verify", token);
        //将验证码图片响应给浏览器
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            ImageIO.write(verifyCodeImage,"JPEG",outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //获取当前时间
    @GetMapping("/getTime")
    public Result<DateNow> getTime(){
        LocalDateTime time = LocalDateTime.now();
        DateNow dateNow = new DateNow();
        dateNow.setYear(time.getYear());
        dateNow.setMonth(time.getMonthValue());
        dateNow.setDay(time.getDayOfMonth());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE").withLocale(Locale.CHINA);
        String week = time.format(formatter);
        dateNow.setWeek(week);
        return Result.ok(dateNow);
    }

    //获取邮箱验证码
    @GetMapping("/getEmailVerifyCode/{email}")
    public Result<String> getEmailVerifyCode(@PathVariable("email") String email){
        SendEmail.sendEmail(email);
        String emailVerifyCode = SendEmail.getEmailVerifyCode();
        String emailVerify = JwtHelper.createToken(emailVerifyCode);
        return Result.ok(emailVerify);
    }

    //获取短信验证码
    @GetMapping("/getMessageVerifyCode/{phoneNumber}")
    public Result<String> getMessageVerifyCode(@PathVariable("phoneNumber") String phoneNumber) {
        SendMessage.sendMessage(phoneNumber);
        String messageVerifyCode = SendMessage.getMessageVerifyCode();
        String messageVerify = JwtHelper.createToken(messageVerifyCode);
        return Result.ok(messageVerify);
    }

    //获取指定路径下的图片
    @GetMapping("/getPicture/{pictureName}")
    public void getPicture(
            @PathVariable("pictureName") String pictureName,
            HttpServletResponse response
    ){
        try {
            String picturePath = "D:\\大学\\大四\\毕业设计\\项目代码\\趣集后端代码\\fun-market\\src\\main\\webapp\\uploadPicture\\".concat(pictureName);
            //根据路径获取图片
            BufferedImage image = ImageIO.read(new File(picturePath));
            ServletOutputStream outputStream = response.getOutputStream();
            //获取图片的后缀
            String fileType = picturePath.substring(picturePath.lastIndexOf(".") + 1);
            ImageIO.write(image, fileType, outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

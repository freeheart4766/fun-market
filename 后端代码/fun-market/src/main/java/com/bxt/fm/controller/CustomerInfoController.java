package com.bxt.fm.controller;

import com.bxt.fm.pojo.CustomerInfo;
import com.bxt.fm.service.CustomerInfoService;
import com.bxt.fm.util.JwtHelper;
import com.bxt.fm.util.Result;
import com.bxt.fm.util.ResultCodeEnum;
import com.bxt.fm.util.UploadFile;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/customerInfo")
public class CustomerInfoController {

    @Resource(name = "customerInfoService")
    private CustomerInfoService customerInfoService;

    //获取用户的昵称
    @GetMapping("/getCustomerName")
    public Result<String> getCustomerName(@RequestHeader("token") String token){
        String signId = JwtHelper.getSignId(token);
        if (signId != null) {
            int customerId = Integer.parseInt(signId);
            return customerInfoService.getCustomerName(customerId);
        }
        return Result.build(null, ResultCodeEnum.LOGIN_AUTH);
    }

    //获取用户的基本信息
    @GetMapping("/getCustomerInfo")
    public Result<CustomerInfo> getCustomerInfo(@RequestHeader("token") String token){
        String signId = JwtHelper.getSignId(token);
        if (signId != null) {
            int customerId = Integer.parseInt(signId);
            return customerInfoService.getCustomerInfo(customerId);
        }
        return Result.build(null, ResultCodeEnum.LOGIN_AUTH);
    }

    //修改用户的基本信息
    @PutMapping("/updateCustomerInfo")
    public Result<Integer> updateCustomerInfo(
            @RequestHeader("token") String token,
            @RequestBody CustomerInfo customerInfo
    ){
        String signId = JwtHelper.getSignId(token);
        if (signId != null) {
            int customerId = Integer.parseInt(signId);
            customerInfo.setCustomerId(customerId);
            return customerInfoService.updateCustomerInfo(customerInfo);
        }
        return Result.build(null, ResultCodeEnum.LOGIN_AUTH);
    }

    //头像上传
    @PostMapping("/uploadPicture")
    public Result<String> uploadPicture(
            @RequestHeader("token") String token,
            @RequestParam("image") MultipartFile multipartFile
    ){
        String signId = JwtHelper.getSignId(token);
        if (signId != null) {
            int customerId = Integer.parseInt(signId);
            if (multipartFile == null){
                return Result.build(null,ResultCodeEnum.UPLOAD_FAIL);
            }
            int checkFormat = UploadFile.checkFormat(multipartFile);
            if (checkFormat == 207){
                return Result.build(null,ResultCodeEnum.PICTURE_LARGE);
            }else if (checkFormat == 208){
                return Result.build(null,ResultCodeEnum.PICTURE_TYPE);
            }
            String picturePath = UploadFile.savePicture(multipartFile);
            return customerInfoService.uploadPicture(picturePath,customerId);
        }
        return Result.build(null, ResultCodeEnum.LOGIN_AUTH);
    }

    //获取头像
    @GetMapping("/getHeadPicture")
    public Result<String> getHeadPicture(
            @RequestHeader("token") String token,
            HttpServletResponse response
    ) {
        String signId = JwtHelper.getSignId(token);
        if (signId != null) {
            int customerId = Integer.parseInt(signId);
            //获取头像的路径
            String picturePath = customerInfoService.getHeadPicture(customerId);
            if (picturePath == null || picturePath.equals("")) {
                return Result.build(null, ResultCodeEnum.DEFAULT_PICTURE);
            }
            //将图片响应给客户端
            try {
                //根据路径获取图片
                BufferedImage image = ImageIO.read(new File(picturePath));
                ServletOutputStream outputStream = response.getOutputStream();
                //获取图片的后缀
                String fileType = picturePath.substring(picturePath.lastIndexOf(".") + 1);
                ImageIO.write(image, fileType, outputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return Result.ok();
        }
        return Result.build(null, ResultCodeEnum.LOGIN_AUTH);
    }


}

package com.bxt.fm.util;

//文件上传的工具类

import org.apache.tika.Tika;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class UploadFile {

    //校验所上传图片的大小及格式
    public static int checkFormat(MultipartFile multipartFile){
        //检查图片大小为5M
        int MAX_SIZE = 5 * 1024 * 1024;
        if (multipartFile.getSize() > MAX_SIZE){
            return 207;
        }
        //检查上传的文件类型
        Tika tika = new Tika();
        //允许的文件类型列表
        List<String> allowedMimeTypes = Arrays.asList("image/jpg", "image/jpeg", "image/png");
        try {
            InputStream inputStream = multipartFile.getInputStream();
            //获取文件的MIME类型
            String fileType = tika.detect(inputStream);
            if (!allowedMimeTypes.contains(fileType)){
                return 208;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return 200;
    }

    //将图片保存到服务器上，并返回url
    public static String savePicture(MultipartFile multipartFile){
        //获取文件的原始名称
        String originalFilename = multipartFile.getOriginalFilename();
        //获取文件的后缀
        if (originalFilename != null) {
            int index = originalFilename.lastIndexOf('.');
            String suffix = originalFilename.substring(index);
            //设置文件的新名字
            String newPictureName = (UUID.randomUUID() + suffix).replaceAll("-","");
            //设置文件保存的路径
            String uploadDirection = "D:\\大学\\大四\\毕业设计\\项目代码\\趣集后端代码\\fun-market\\src\\main\\webapp\\uploadPicture\\";
            //将文件保存到服务器中
            try {
                multipartFile.transferTo(new File(uploadDirection + newPictureName));
            } catch (IOException e) {
                throw new RuntimeException("文件保存失败: " + e);
            }
            return uploadDirection + newPictureName;
        }
        return null;
    }
}

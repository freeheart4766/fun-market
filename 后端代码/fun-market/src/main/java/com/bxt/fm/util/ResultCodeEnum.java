package com.bxt.fm.util;

//响应结果类型码枚举

import lombok.Getter;

//统一返回结果状态信息类
@Getter
public enum ResultCodeEnum {

    SUCCESS(200,"成功"),
    FAIL(201, "失败"),
    USERNAME_NONE(202,"用户名不存在"),
    LOGIN_ERROR(203, "用户名或密码错误"),
    CODE_ERROR(204,"验证码错误!"),
    USERNAME_OCCUPY(205,"用户名已占用"),
    LOGIN_AUTH(206, "用户未登陆"),
    PICTURE_LARGE(207,"图片体积过大"),
    PICTURE_TYPE(208,"文件格式有误"),
    UPLOAD_FAIL(209,"文件上传失败"),
    DEFAULT_PICTURE(210,"默认头像"),
    EMAIL_VERIFY_ERROR(211,"验证码发送失败"),
    PASSWORD_CHANGE_ERROR(212,"密码修改失败"),
    AUTHENTICATION_FAIL(213,"实名信息保存失败"),
    ADD_ADDRESS_FAIL(214,"收货信息添加失败"),
    UPDATE_ADDRESS_FAIL(215,"收货信息修改失败"),
    DELETE_ADDRESS_FAIL(216,"收货信息删除失败");


    private final Integer code;
    private final String message;
    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

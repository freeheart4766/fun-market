package com.bxt.fm.util;

import lombok.Data;
import lombok.NoArgsConstructor;

//全局统一返回结果类
//为了将前端返回的结果统一格式，所有前端返回的结果都是一个Result对象，包含状态码、状态信息、数据
@Data
@NoArgsConstructor
public class Result<T> {

    //状态码
    private Integer code;
    //状态信息
    private String message;
    //数据
    private T data;

    //静态工厂方法(构建器模式)
    //返回结果(仅包含数据)
    protected static <T> Result<T> build(T data){
        Result<T> result = new Result<>();
        if (data != null){
            result.setData(data);
        }
        return result;
    }

    //返回结果(包含状态码、状态信息、数据)
    public static <T> Result<T> build(T body,ResultCodeEnum resultCodeEnum){
        Result<T> result = build(body);
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        return result;
    }

    //操作成功
    public static <T> Result<T> ok(T data){
        return build(data,ResultCodeEnum.SUCCESS);
    }
    public static <T> Result<T> ok(){
        return Result.ok(null);
    }

    //操作失败
    public static <T> Result<T> fail(T data){
        return build(data,ResultCodeEnum.FAIL);
    }
    public static <T> Result<T> fail(){
        return Result.fail(null);
    }

    //判断是否为一个成功的操作
    public boolean isOk(){
        return this.getCode().intValue() == ResultCodeEnum.SUCCESS.getCode().intValue();
    }

}
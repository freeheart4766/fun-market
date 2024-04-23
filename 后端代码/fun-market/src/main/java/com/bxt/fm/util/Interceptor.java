package com.bxt.fm.util;

//拦截器类

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class Interceptor implements HandlerInterceptor {

    //在控制器方法执行之前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (token != null){
            boolean tokenExpiration = JwtHelper.isExpiration(token);
            return !tokenExpiration;
        }
        //true是放行
        return true;
    }
}

package com.bxt.fm.config;

//主要配置controller和springmvc相关组件

import com.bxt.fm.util.Interceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan("com.bxt.fm.controller")
@EnableWebMvc   //启用SpringMVC框架的默认配置，包括消息转换器、格式化器以及验证器等，此外还启用了SpringMVC的注解驱动。
public class WebConfig implements WebMvcConfigurer {

    //配置文件上传解析器
    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    //配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //将拦截器添加到SpringMVC环境中，默认拦截所有请求
        registry.addInterceptor(new Interceptor());
    }

    //配置编码过滤器
    @Bean
    public CharacterEncodingFilter characterEncodingFilter(){
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceResponseEncoding(true);
        return filter;
    }
}

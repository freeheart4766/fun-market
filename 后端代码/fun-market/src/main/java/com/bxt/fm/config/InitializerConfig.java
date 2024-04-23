package com.bxt.fm.config;

import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//初始化配置类
public class InitializerConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    //用于指定root容器的配置类，包括数据源（DataSource）、服务（Service）和映射器（Mapper）的配置
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {
                SpringConfig.class,
                MybatisConfig.class,
                DataSourceConfig.class
        };
    }

    //用于指定Web容器的配置类，通常包括SpringMVC的配置
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebConfig.class };
    }

    //用于指定DispatcherServlet的处理路径
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    //配置文件上传的相关参数
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(
                new MultipartConfigElement("D:\\大学\\大四\\毕业设计\\项目代码\\趣集后端代码\\fun-market\\src\\main\\webapp\\uploadTemp\\",
                        5*1024*1024,
                        7*1024*1024,
                        0
                )
        );
    }

}

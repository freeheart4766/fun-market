package com.bxt.fm.config;

//主要配置mapper代理对象和mybatis核心组件

import org.apache.ibatis.session.AutoMappingBehavior;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MybatisConfig {

    //配置SqlSessionFactoryBean，指定连接池对象和外部配置文件
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource){
        //实例化SqlSessionFactory工厂
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        //设置连接池
        sqlSessionFactoryBean.setDataSource(dataSource);

        //创建一个Configuration对象，用于存储MyBatis的所有配置信息
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        //开启驼峰式映射
        configuration.setMapUnderscoreToCamelCase(true);
        //开启resultMap自动映射
        configuration.setAutoMappingBehavior(AutoMappingBehavior.FULL);
        //将MyBatis的配置信息设置给SqlSessionFactoryBean
        sqlSessionFactoryBean.setConfiguration(configuration);

        //为此包下所有的类设置别名
        sqlSessionFactoryBean.setTypeAliasesPackage("com.bxt.fm.pojo");

        return sqlSessionFactoryBean;
    }

    //配置MyBatis的映射器扫描器，自动扫描包下的接口，并将它们注册为MyBatis的映射器
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        //mapper代理对象的FactoryBean，将指定包下的mapper接口全部生成mapper代理对象
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.bxt.fm.mapper");
        return mapperScannerConfigurer;
    }
}

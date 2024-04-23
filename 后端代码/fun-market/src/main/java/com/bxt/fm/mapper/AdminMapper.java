package com.bxt.fm.mapper;

import com.bxt.fm.pojo.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("adminMapper")
public interface AdminMapper {

    //检查是否有此用户名
    Admin existUsername(@Param("username") String username);

    //登录
    Admin login(@Param("username") String username);

    //注册
    void register(@Param("username") String username,@Param("password") String password);
}

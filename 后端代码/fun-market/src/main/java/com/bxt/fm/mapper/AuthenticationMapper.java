package com.bxt.fm.mapper;

import com.bxt.fm.pojo.Authentication;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("authenticationMapper")
public interface AuthenticationMapper {

    //获取是否实名的信息
    String getAuthenticationStatus(@Param("customerId") int customerId);

    //保存实名信息
    int addAuthentication(@Param("authentication") Authentication authentication);
}

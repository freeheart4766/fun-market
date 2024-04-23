package com.bxt.fm.mapper;

import com.bxt.fm.pojo.Customer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("customerMapper")
public interface CustomerMapper {

    //检查是否有此用户名
    Customer existUsername(@Param("username") String username);

    //登录，检查用户名，密码和验证码是否正确
    Customer login(@Param("username") String username);

    //注册
    void register(@Param("username") String username,@Param("password") String password);

    //修改密码
    int changePassword(@Param("customerId") int customerId,@Param("passwordNew") String passwordNew);

    //修改支付密码
    int changePayPassword(@Param("customerId") int customerId,@Param("passwordNew") String passwordNew);
}

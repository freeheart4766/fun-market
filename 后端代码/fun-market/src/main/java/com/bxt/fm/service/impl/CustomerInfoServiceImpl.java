package com.bxt.fm.service.impl;

import com.bxt.fm.mapper.CustomerInfoMapper;
import com.bxt.fm.pojo.CustomerInfo;
import com.bxt.fm.service.CustomerInfoService;
import com.bxt.fm.util.Result;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("customerInfoService")
public class CustomerInfoServiceImpl implements CustomerInfoService {

    @Resource(name = "customerInfoMapper")
    private CustomerInfoMapper customerInfoMapper;

    //获取用户的昵称
    @Override
    public Result<String> getCustomerName(int customerId) {
        String nickname = customerInfoMapper.getCustomerName(customerId);
        if (nickname == null){
            String nicknameNew = "趣集用户".concat(String.valueOf(customerId));
            //将customerId和昵称一起存入数据库
            customerInfoMapper.addCustomerNickname(customerId,nicknameNew);
            return Result.ok(nicknameNew);
        }
        return Result.ok(nickname);
    }

    //获取用户的基本信息
    @Override
    public Result<CustomerInfo> getCustomerInfo(int customerId) {
        CustomerInfo customerInfo = customerInfoMapper.getCustomerInfo(customerId);
        return Result.ok(customerInfo);
    }

    //修改用户的基本信息
    @Override
    public Result<Integer> updateCustomerInfo(CustomerInfo customerInfo) {
        int updateNumber = customerInfoMapper.updateCustomerInfo(customerInfo);
        if(updateNumber != 0){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    //将图片的路径保存到数据库中
    @Override
    public Result<String> uploadPicture(String picturePath, int customerId) {
        customerInfoMapper.uploadPicture(picturePath,customerId);
        return Result.ok();
    }

    //获取头像路径
    @Override
    public String getHeadPicture(int customerId) {
        return customerInfoMapper.getHeadPicture(customerId);
    }

}

package com.bxt.fm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data    //Data注解相当于类中自动生成了getter、setter、equals、hashCode、toString方法
@AllArgsConstructor    //相当于有了一个全参数的构造方法
@NoArgsConstructor    //相当于有了一个无参数的构造方法
public class Customer {

    private Integer customerId;
    private String customerUsername;
    private String customerPassword;
}

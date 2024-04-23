package com.bxt.fm.util;

//获取当前时间的工具类

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GetTimeNow {

    //获取具体的时间
    public static String getTime(){
        //获取当前的时间：2024-03-16T17:17:58
        LocalDateTime time = LocalDateTime.now();
        //指定日期和时间的格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //将日期和时间转化为String型
        return time.format(formatter);
    }

    //获取年月日
    public static String getDate(){
        //获取当前的时间：2024-03-16T17:17:58
        LocalDateTime time = LocalDateTime.now();
        //指定日期和时间的格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //将日期和时间转化为String型
        return time.format(formatter);
    }
}

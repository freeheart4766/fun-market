package com.bxt.fm.util;

//验证码图片工具类

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class CreateVerifyCodeImage {
    private static final int WIDTH = 100;   //图片宽度
    private static final int HEIGHT = 40;   //图片高度
    private static final int FONT_SIZE = 20;    //字体大小
    private static char[] verifyCode;   //验证码
    private static BufferedImage verifyCodeImage;   //验证码图片

    //获取验证码图片
    public static BufferedImage getVerifyCodeImage(){
        //创建一张图片
        //BufferedImage用于在内存中创建、编辑和操作图像
        //TYPE_INT_BGR表示图像将使用BGR（蓝、绿、红）格式的整数数组来存储每个像素的颜色信息
        verifyCodeImage = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_BGR);
        //Graphics对象用于在图像上进行绘图，通过getGraphics()获取了BufferedImage的Graphics对象后，就可以使用它的各种方法在BufferedImage上绘制内容
        Graphics graphics = verifyCodeImage.getGraphics();
        //生成4个随机的验证码
        verifyCode = generateCheckCode();
        //绘制验证码背景
        drawBackground(graphics);
        //绘制验证码
        drawRands(graphics,verifyCode);
        //释放与Graphics对象相关的系统资源
        graphics.dispose();
        return verifyCodeImage;
    }

    //获取验证码
    public static char[] getVerifyCode(){
        return verifyCode;
    }

    //随机生成验证码
    private static char[] generateCheckCode(){
        //生成随机验证码的字符池
        String chars = "0123456789" + "abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] rands = new char[4];
        for (int i = 0; i < 4; i++){
            //Math.random()生成一个[0,1)之间的随机浮点数,rand是一个[0,61]之间的随机整数。
            int rand = (int) (Math.random()*(10+26+26));
            rands[i] = chars.charAt(rand);
        }
        return rands;
    }

    //绘制验证码
    private static void drawRands(Graphics graphics, char[] rands){
        //设置字体样式
        graphics.setFont(new Font("Console",Font.BOLD,FONT_SIZE));
        for (int i = 0; i < rands.length; i++){
            graphics.setColor(getRandomColor());
            graphics.drawString(String.valueOf(rands[i]), i * FONT_SIZE + 10, 30);
        }
    }

    //获取随机颜色
    private static Color getRandomColor(){
        Random ran = new Random();
        return new Color(ran.nextInt(220),ran.nextInt(220),ran.nextInt(220));
    }

    //绘制图片验证码背景
    private static void drawBackground(Graphics graphics){
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0,0,WIDTH,HEIGHT);
        //绘制验证码干扰点
        for (int i = 0; i < 200; i++){
            int x = (int) (Math.random() * WIDTH);
            int y = (int) (Math.random() * HEIGHT);
            graphics.setColor(getRandomColor());
            //在(x,y)位置绘制一个1x1像素大小的圆点
            graphics.drawOval(x,y,1,1);
        }
    }
}

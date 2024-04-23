package com.bxt.fm.util;

//发送邮件的工具类

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class SendEmail {
    private static final String senderEmail = "2605484942@qq.com";    //发件人邮箱
    private static final String authorizationCode = "syvyeycmaggwebge";    //QQ邮箱授权码
    private static final String host = "smtp.qq.com";    //发送邮件的主机位QQ邮件服务器
    private static String emailVerifyCode;

    //发送邮件
    public static void sendEmail(String recipientEmail){
        //存储SMTP服务器的配置信息
        Properties properties = new Properties();
        //设置SMTP服务器需要身份验证
        properties.put("mail.smtp.auth","true");
        //启用TLS（传输层安全）加密
        properties.put("mail.smtp.starttls.enable", "true");
        //SMTP服务器的主机名
        properties.put("mail.smtp.host", host);
        //设置SMTP服务器的端口号，587端口是QQ邮箱SMTP服务推荐的端口，用于TLS加密的连接
        properties.put("mail.smtp.port", "587");

        //获取默认的session对象
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail,authorizationCode);
            }
        });

        try {
            //创建默认的MimeMessage对象
            MimeMessage message = new MimeMessage(session);
            //设置发件人
            message.setFrom(new InternetAddress(senderEmail));
            //设置收件人
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(recipientEmail));
            //设置邮件主题
            message.setSubject("趣集网邮箱验证");
            //获取验证码
            emailVerifyCode = generateCheckCode();
            //邮件正文的内容
            String messageContent =
                    "<div>\n" +
                    "<strong style=\"color: rgb(100, 100, 100);\">亲爱的用户: <span style=\"margin-left: 10px;\">您好！</span></strong><br>\n" +
                    "<strong style=\"color: rgb(100, 100, 100);line-height: 50px;\">您正在进行趣集网邮箱验证，您的验证码为</strong><br>\n" +
                    "<h1 style=\"color: orangered;\">" + emailVerifyCode + "</h1>\n" +
                    "<strong style=\"color: rgb(100, 100, 100);line-height: 50px;\">请在30分钟内完成验证</strong>\n" +
                    "</div>";
            //设置邮件正文，内容类型为html
            message.setContent(messageContent,"text/html; charset=UTF-8");
            //发送消息
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    //获取邮件的验证码
    public static String getEmailVerifyCode(){
        return emailVerifyCode;
    }

    //随机生成验证码
    private static String generateCheckCode(){
        String chars = "0123456789";
        char[] rands = new char[6];
        for (int i = 0; i < 6; i++){
            //Math.random()生成一个[0,1)之间的随机浮点数,rand是一个[0,9]之间的随机整数。
            int rand = (int) (Math.random()*10);
            rands[i] = chars.charAt(rand);
        }
        return String.valueOf(rands);
    }
}

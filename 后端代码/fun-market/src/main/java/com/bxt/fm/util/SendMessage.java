package com.bxt.fm.util;

//发送短信的工具类

import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.sdk.service.dysmsapi20170525.AsyncClient;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsResponse;
import com.google.gson.Gson;
import darabonba.core.client.ClientOverrideConfiguration;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class SendMessage {
    private static final String REGION_ID = "cn-hangzhou";    //服务的区域id：杭州
    private static final String SIGN_NAME = "趣集网";    //签名名称
    private static final String TEMPLATE_CODE = "SMS_465412366";    //短信模板的编号
    private static String messageVerifyCode;

    //发送短信
    public static void sendMessage(String phoneNumber) {
        //创建了一个Credential对象，包含了访问阿里云服务的凭证信息
        Credential credential = Credential.builder()
                .accessKeyId(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID"))
                .accessKeySecret(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET"))
                .build();
        //使用上面创建的credential对象来创建一个StaticCredentialProvider对象，用于提供凭证给客户端
        StaticCredentialProvider provider = StaticCredentialProvider.create(credential);
        //使用AsyncClient.builder()构建了一个异步客户端对象，用于与阿里云短信服务进行交互
        AsyncClient client = AsyncClient.builder()
                //设置服务的区域id，确保客户端连接到正确的阿里云区域
                .region(REGION_ID)
                //设置凭证提供者，使得客户端可以使用正确的凭证进行身份验证
                .credentialsProvider(provider)
                //覆盖默认的配置，设置服务的端点地址
                .overrideConfiguration(
                        ClientOverrideConfiguration.create()
                                .setEndpointOverride("dysmsapi.aliyuncs.com")
                )
                .build();
        //获取验证码
        messageVerifyCode = generateCheckCode();
        // 构建发送短信的请求对象
        SendSmsRequest sendSmsRequest = SendSmsRequest.builder()
                .phoneNumbers(phoneNumber)
                .signName(SIGN_NAME)
                .templateCode(TEMPLATE_CODE)
                .templateParam("{\"code\":\"" + messageVerifyCode + "\"}")
                .build();
        //执行发送的操作，并获取发送的结果
        CompletableFuture<SendSmsResponse> response = client.sendSms(sendSmsRequest);
        System.out.println(response);
        try {
            SendSmsResponse smsResponse = response.get();
            String sendResult = new Gson().toJson(smsResponse);
            System.out.println(sendResult);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        //关闭异步客户端
        client.close();
    }

    //获取短信的验证码
    public static String getMessageVerifyCode(){
        return messageVerifyCode;
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

package com.wonder.bjutcats.controller;

import com.wonder.bjutcats.pojo.User;
import com.wonder.bjutcats.services.ObjectServices;
import com.wonder.bjutcats.util.Result;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@RestController
public class Login {

    // 微信小程序相关信息
    private String appid = "wxc25236d313fd5b23";
    private String secret = "17612470f4ebd8b55535e5c9761345c2";
    private ObjectServices objectServices;

    // 小程序端口先进行wx.login()获取凭证code发送到服务器
    @RequestMapping(value = "login" , method = RequestMethod.GET)
    public Result Login(String code){
        try{
            // 拼接请求的url
            String url = "https://api.weixin.qq.com/sns/jscode2session?appid={0}&secret={1}&js_code={2}&grant_type=authorization_code";
            String replaceUrl = url.replace("{0}", appid).replace("{1}", secret).replace("{2}", code);
            // 发送GET请求
            WebClient webClient = WebClient.create(replaceUrl);
            @Data
            @AllArgsConstructor
            @NoArgsConstructor
            class UserToken{
                private String openid;
                private String session_key;
            }     // 用户openid和sessionkey的载体
            UserToken tmp = webClient.get().retrieve()
                    .bodyToMono(UserToken.class).block();     // tmp中存储当前请求的用户的OpenID和SessionKey
            // 在数据库中查询该id是否在数据库中，如果没有则添加条目
            if(objectServices.getUserInfo(tmp.getOpenid()).getId() == ""){
                objectServices.insertUserInfo(tmp.getOpenid() , "" , 2 , "" , "" , tmp.getSession_key());
            } else{
                objectServices.updateUserToken(tmp.getOpenid() , tmp.getSession_key());
            }
            return Result.success(tmp);
        } catch(Exception e){
            log.info("error happened: " , e);
            return Result.error("something wrong");
        }
    }

}

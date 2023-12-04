package com.wonder.bjutcats.controller;

import com.wonder.bjutcats.services.ObjectServices;
import com.wonder.bjutcats.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UserData {

    @Autowired
    private ObjectServices objectServices;

    // 传入用户id和图片文件存储用户头像，返回存储的url地址
    @RequestMapping(value = "/userinfo/image" , method = RequestMethod.POST , consumes = "multipart/form-data")
    public Result upload(@RequestParam("userid") Integer userid , @RequestParam("image") MultipartFile files){
        // 调用Services层方法进行存储
        String target = objectServices.setUserImage(userid , files);

        // 将存储url响应回客户端
        return Result.success(target);
    }



}

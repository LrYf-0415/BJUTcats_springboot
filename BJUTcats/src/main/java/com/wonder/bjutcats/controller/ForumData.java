package com.wonder.bjutcats.controller;

import com.wonder.bjutcats.services.ForumServices;
import com.wonder.bjutcats.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ForumData {

    @Autowired
    ForumServices forumServices;

    @RequestMapping(value = "/forum/posting" , method = RequestMethod.POST)
    public Result upload(@RequestParam("postid") Integer postid , @RequestParam("image")MultipartFile files){
        // 调用Services层方法进行存储
        String target = forumServices.setUPostImage(postid , files);

        // 将存储url响应回客户端
        return Result.success(target);
    }

}

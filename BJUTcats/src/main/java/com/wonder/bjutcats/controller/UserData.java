package com.wonder.bjutcats.controller;

import com.wonder.bjutcats.pojo.User;
import com.wonder.bjutcats.services.ObjectServices;
import com.wonder.bjutcats.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UserData {

    @Autowired
    private ObjectServices objectServices;

    @RequestMapping(value = "/user/info" , method = RequestMethod.GET)
    public Result getUserInfo(Integer id){
        // 调用Services层方法
        User target = objectServices.getUserInfo(id);
        // 响应客户端
        return Result.success(target);
    }

    @RequestMapping(value = "/user/newuser" , method = RequestMethod.POST)
    public Result newUserInfo(String username , Integer gender , String emails , String phone){
        // 调用Services层方法
        try{
            Integer mark = objectServices.insertUserInfo(username , gender , emails , phone);
            if(mark == 0){
                return Result.success(0);
            }
            else{
                return Result.error("error: update");
            }
        } catch(Exception e){
            e.printStackTrace();
            return Result.error("error: update");
        }
    }

    @RequestMapping(value = "/user/setinfo" , method = RequestMethod.POST)
    public Result setUserInfo(Integer id , String username , Integer gender , String emails , String phone){
        // 调用Services层方法
        try{
            Integer mark = objectServices.updateUserInfo(id , username , gender , emails , phone);
            if(mark == 0){
                return Result.success(0);
            }
            else{
                return Result.error("error: update");
            }
        } catch(Exception e){
            e.printStackTrace();
            return Result.error("error: update");
        }
    }

    // 传入用户id和图片文件存储用户头像，返回存储的url地址
    @RequestMapping(value = "/user/image" , method = RequestMethod.POST , consumes = "multipart/form-data")
    public Result upload(@RequestParam("userid") Integer userid , @RequestParam("image") MultipartFile files){
        // 调用Services层方法进行存储
        String target = objectServices.setUserImage(userid , files);

        // 将存储url响应回客户端
        return Result.success(target);
    }



}

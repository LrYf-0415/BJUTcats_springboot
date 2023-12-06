package com.wonder.bjutcats.controller;

import com.wonder.bjutcats.pojo.User;
import com.wonder.bjutcats.services.ObjectServices;
import com.wonder.bjutcats.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class UserData {

    @Autowired
    private ObjectServices objectServices;

    @RequestMapping(value = "/user/info" , method = RequestMethod.GET)
    public Result getUserInfo(String id){
        try{
            // 调用Services层方法
            User target = objectServices.getUserInfo(id);
            // 响应客户端
            return Result.success(target);
        } catch(Exception e){
            log.info("error happend: " , e);
            return Result.error("something error happened");
        }
    }

//    @RequestMapping(value = "/user/newuser" , method = RequestMethod.POST)
//    public Result newUserInfo(@RequestBody User user){
//        // 调用Services层方法
//        try{
//            Integer mark = objectServices.insertUserInfo(user.getId() , user.getUsername() , user.getGender() , user.getEmails() , user.getPhone() , user.getToken());
//            if(mark == 0){
//                return Result.success(0);
//            }
//            else{
//                return Result.error("error: update");
//            }
//        } catch(Exception e){
//            log.info("error happend: " , e);
//            return Result.error("something error happened");
//        }
//    }

    @RequestMapping(value = "/user/setinfo" , method = RequestMethod.POST)
    public Result setUserInfo(@RequestBody User user){
        // 调用Services层方法
        try{
            Integer mark = objectServices.updateUserInfo(user.getId() , user.getUsername() , user.getGender() , user.getEmails() , user.getPhone());
            if(mark == 0){
                return Result.success(0);
            }
            else{
                return Result.error("error: update");
            }
        } catch(Exception e){
            log.info("error happend: " , e);
            return Result.error("something error happened");
        }
    }

    // 传入用户id和图片文件存储用户头像，返回存储的url地址
    @RequestMapping(value = "/user/image" , method = RequestMethod.POST , consumes = "multipart/form-data")
    public Result upload(@RequestParam("userid") String userid , @RequestParam("image") MultipartFile files){
        try{
            // 调用Services层方法进行存储
            String target = objectServices.setUserImage(userid , files);
            // 将存储url响应回客户端
            return Result.success(target);
        } catch(Exception e){
            log.info("error happend: " , e);
            return Result.error("something error happened");
        }
    }



}

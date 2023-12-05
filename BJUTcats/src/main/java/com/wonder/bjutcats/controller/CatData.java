package com.wonder.bjutcats.controller;

import com.wonder.bjutcats.pojo.Cat;
import com.wonder.bjutcats.pojo.Meal;
import com.wonder.bjutcats.pojo.Posting;
import com.wonder.bjutcats.services.ForumServices;
import com.wonder.bjutcats.services.ObjectServices;
import com.wonder.bjutcats.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
public class CatData {

    @Autowired
    private ObjectServices objectServices;
    @Autowired
    private ForumServices forumServices;

    @RequestMapping(value = "catinfo/list" , method = RequestMethod.GET)
    public Result getCatList(Integer index){
        try{
            // 调用Services层方法获取小猫列表
            List<Cat> result = objectServices.getCatList(index);
            // 发送响应信息
            return Result.success(result);
        } catch(Exception e){
            log.info("error happend: " , e);
            return Result.error("something error happened");
        }
    }

    @RequestMapping(value = "catinfo/detail" , method = RequestMethod.GET)
    public Result getCatInfo(Integer id){
        try{
            // 调用Services层方法获取小猫信息
            Cat result = objectServices.getCat(id);
            // 发送响应信息
            return Result.success(result);
        } catch(Exception e){
            log.info("error happend: " , e);
            return Result.error("something error happened");
        }
    }

    @RequestMapping(value = "catinfo/food" , method = RequestMethod.GET)
    public Result getCatFood(Integer id){
        try{
            // 调用Service层方法获取小猫信息
            List<Meal> result = forumServices.getFeedByCat(id);
            // 发送响应信息
            return Result.success(result);
        } catch(Exception e){
            log.info("error happend: " , e);
            return Result.error("something error happened");
        }
    }

    @RequestMapping(value = "catinfo/tweet" , method = RequestMethod.GET)
    public Result getCatPosts(Integer id){
        try{
            // 调用Services层方法获取小猫信息
            List<Posting> result = forumServices.getPostByCat(id);
            // 发送响应信息
            return Result.success(result);
        } catch(Exception e){
            log.info("error happend: " , e);
            return Result.error("something error happened");
        }
    }

    // 小猫图片上传
    @RequestMapping(value = "catinfo/image" , method = RequestMethod.POST , consumes = "multipart/form-data")
    public Result upload(@RequestParam("catid") Integer catid , @RequestParam("image") MultipartFile files){
        try{
            // 调用Services层方法
            String target = objectServices.setCatImage(catid , files);
            // 返回响应
            return Result.success(target);
        } catch(Exception e){
            log.info("error happend: " , e);
            return Result.error("something error happened");
        }
    }

}

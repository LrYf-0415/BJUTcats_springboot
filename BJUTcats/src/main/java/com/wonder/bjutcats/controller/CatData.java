package com.wonder.bjutcats.controller;

import com.wonder.bjutcats.pojo.Cat;
import com.wonder.bjutcats.pojo.Meal;
import com.wonder.bjutcats.pojo.Posting;
import com.wonder.bjutcats.services.ForumServices;
import com.wonder.bjutcats.services.ObjectServices;
import com.wonder.bjutcats.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CatData {

    @Autowired
    private ObjectServices objectServices;
    private ForumServices forumServices;

    @RequestMapping(value = "catinfo/list" , method = RequestMethod.GET)
    public Result getCatList(Integer index){
        // 调用Services层方法获取小猫列表
        List<Cat> result = objectServices.getCatList(index);
        // 发送响应信息
        return Result.success(result);
    }

    @RequestMapping(value = "catinfo/detail" , method = RequestMethod.GET)
    public Result getCatInfo(Integer id){
        // 调用Services层方法获取小猫信息
        Cat result = objectServices.getCat(id);
        // 发送响应信息
        return Result.success(result);
    }

    @RequestMapping(value = "catinfo/food" , method = RequestMethod.GET)
    public Result getCatFood(Integer id){
        // 调用Service层方法获取小猫信息
        List<Meal> result = forumServices.getFeedByCat(id);
        // 发送响应信息
        return Result.success(result);
    }

    @RequestMapping(value = "catinfo/tweet" , method = RequestMethod.GET)
    public Result getCatPosts(Integer id){
        // 调用Services层方法获取小猫信息
        List<Posting> result = forumServices.getPostByCat(id);
        // 发送响应信息
        return Result.success(result);
    }

}

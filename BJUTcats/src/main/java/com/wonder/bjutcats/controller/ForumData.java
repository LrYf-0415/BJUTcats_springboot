package com.wonder.bjutcats.controller;

import com.wonder.bjutcats.pojo.Meal;
import com.wonder.bjutcats.pojo.Posting;
import com.wonder.bjutcats.services.ForumServices;
import com.wonder.bjutcats.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
public class ForumData {

    @Autowired
    ForumServices forumServices;

    @RequestMapping(value = "/socialinfo/tweet" , method = RequestMethod.GET)
    public Result getPostsByUser(String id){
        try{
            // 调用Services层方法
            List<Posting> result = forumServices.getPostByUser(id);
            return Result.success(result);
        } catch(Exception e){
            log.info("error happend: " , e);
            return Result.error("something error happened");
        }
    }

    @RequestMapping(value = "forum/feed" , method = RequestMethod.POST)
    public Result feedCats(@RequestBody Meal tmp){
        try{
            // 调用Services层方法
            Integer affectrows = forumServices.postFeed(tmp.getUserid() , tmp.getCatid() , tmp.getFood());
            return Result.success(affectrows);
        } catch(Exception e) {
            log.info("error happend: " , e);
            return Result.error("something error happened");
        }
    }

    @RequestMapping(value = "forum/write" , method = RequestMethod.POST)
    public Result writePosts(@RequestBody Posting tmp){
        try{
            // 调用Service层方法
            Integer affectrows = forumServices.postPosting(tmp.getUserid() , tmp.getCatid() , tmp.getContent());
            return Result.success(affectrows);
        } catch(Exception e){
            log.info("error happend: " , e);
            return Result.error("something error happened");
        }
    }

    @RequestMapping(value = "/forum/posting" , method = RequestMethod.POST)
    public Result upload(@RequestParam("postid") Integer postid , @RequestParam("image")MultipartFile files){
        try{
            // 调用Services层方法进行存储
            String target = forumServices.setUPostImage(postid , files);
            // 将存储url响应回客户端
            return Result.success(target);
        } catch(Exception e){
            log.info("error happend: " , e);
            return Result.error("something error happened");
        }
    }

}

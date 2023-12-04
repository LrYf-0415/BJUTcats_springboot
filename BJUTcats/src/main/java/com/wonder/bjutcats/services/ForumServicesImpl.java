package com.wonder.bjutcats.services;

import com.wonder.bjutcats.mapper.FeedMapper;
import com.wonder.bjutcats.mapper.PostMapper;
import com.wonder.bjutcats.mapper.ThumbMapper;
import com.wonder.bjutcats.pojo.Meal;
import com.wonder.bjutcats.pojo.Posting;
import com.wonder.bjutcats.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ForumServicesImpl implements ForumServices {

    @Autowired
    private PostMapper postMapper;
    private FeedMapper feedMapper;

    // 处理GET请求部分
    // 根据小猫id查询小猫被投喂记录
    public List<Meal> getFeedByCat(Integer catid){
        List<Meal> result = feedMapper.getFeedCat(catid);
        return result;
    }

    // 传入小猫id获取该小猫相关动态
    public List<Posting> getPostByCat(Integer catid)
    {
        List<Posting> result = postMapper.getPostCat(catid);
        return result;
    }

    // 传入用户id获取该用户投喂记录
    public List<Meal> getFeedByUser(Integer userid){
        List<Meal> result = feedMapper.getFeedUser(userid);
        return result;
    }

    // 传入用户id获取该用户发布的动态
    public List<Posting> getPostByUser(Integer userid)
    {
        // userid < 0表示获取全部的动态列表
        if(userid < 0){
            List<Posting> result = postMapper.getAllPost();
            return result;
        }
        List<Posting> result = postMapper.getPostUser(userid);
        return result;
    }

    // 处理POST请求部分
    // 根据传入参数添加Feed条目
    public Integer postFeed(Integer userid , Integer catid , String food){
        Integer affectrows = feedMapper.insertFeed(userid , catid , food);
        // return结果为该次请求影响数据库的行数
        return affectrows;
    }

    // 根据传入参数添加Posting条目
    public Integer postPosting(Integer userid , Integer catid , String content){
        Integer affectrows = postMapper.insertPosting(userid , catid , content);
        return affectrows;
    }

    // 传入帖子id修改帖子图片，返回值为图片imageurl
    public String setUPostImage(Integer postid , MultipartFile files){
        // 根据id获取帖子信息
        Posting tmp = postMapper.getPostId(postid);
        // 获取原先文件名称
        String filename = files.getOriginalFilename();
        // 设置在服务器存储位置
        String url = "D:/Files/Programing/BJUTcats/storage/image/posts/" + tmp.getUserid().toString() + tmp.getCatid().toString() + new Date().getTime() + "\\" + filename;
        // 存储在数据库中的url
        String url_db = "/api/storage/image/posts/" + tmp.getUserid().toString() + tmp.getCatid().toString() + new Date().getTime() + "/" + filename;
        // 指定图片存放位置，若目标路径存在图像则覆盖(更新头像)
        File file = new File(url);
        if(!file.exists()){
            file.mkdirs();
        }
        try{
            // 存储图片
            files.transferTo(file);
            postMapper.setUserImageUrl(tmp.getId() , url_db);
        } catch(IOException e){
            e.printStackTrace();
        }
        // 返回图片位置
        return url_db;
    }

}

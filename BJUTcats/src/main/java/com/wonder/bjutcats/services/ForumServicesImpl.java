package com.wonder.bjutcats.services;

import com.wonder.bjutcats.mapper.FeedMapper;
import com.wonder.bjutcats.mapper.PostMapper;
import com.wonder.bjutcats.mapper.ThumbMapper;
import com.wonder.bjutcats.pojo.Meal;
import com.wonder.bjutcats.pojo.Posting;
import com.wonder.bjutcats.pojo.User;
import com.wonder.bjutcats.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class ForumServicesImpl implements ForumServices {

    @Autowired
    private PostMapper postMapper;
    @Autowired
    private FeedMapper feedMapper;

    // 处理GET请求部分
    // 根据小猫id查询小猫被投喂记录
    public List<Meal> getFeedByCat(Integer catid){
        try{
            List<Meal> result = feedMapper.getFeedCat(catid);
            return result;
        } catch(Exception e){
            log.info("error happend: " , e);
            return new ArrayList<Meal>();
        }
    }

    // 传入小猫id获取该小猫相关动态
    public List<Posting> getPostByCat(Integer catid)
    {
        try{
            List<Posting> result = postMapper.getPostCat(catid);
            return result;
        } catch(Exception e){
            log.info("error happend: " , e);
            return new ArrayList<Posting>();
        }
    }

    // 传入用户id获取该用户投喂记录
    public List<Meal> getFeedByUser(String userid){
        try{
            List<Meal> result = feedMapper.getFeedUser(userid);
            return result;
        } catch(Exception e){
            log.info("error happend: " , e);
            return new ArrayList<Meal>();
        }
    }

    // 传入用户id获取该用户发布的动态
    public List<Posting> getPostByUser(String userid)
    {
        try{
            // userid为空串表示获取全部的动态列表
            if(userid == ""){
                List<Posting> result = postMapper.getAllPost();
                return result;
            }
            List<Posting> result = postMapper.getPostUser(userid);
            return result;
        } catch(Exception e){
            log.info("error happend: " , e);
            return new ArrayList<Posting>();
        }
    }

    // 处理POST请求部分
    // 根据传入参数添加Feed条目
    public Integer postFeed(String userid , Integer catid , String food){
        try{
            Integer affectrows = feedMapper.insertFeed(userid , catid , food);
            // return结果为该次请求影响数据库的行数
            return affectrows;
        } catch(Exception e){
            log.info("error happend: " , e);
            return 0;
        }
    }

    // 根据传入参数添加Posting条目
    public Integer postPosting(String userid , Integer catid , String content){
        try{
            Integer affectrows = postMapper.insertPosting(userid , catid , content);
            return affectrows;
        } catch(Exception e){
            log.info("error happend: " , e);
            return 0;
        }
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

package com.wonder.bjutcats.services;

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

    // 传入小猫id获取该小猫的投喂记录
    public List<Meal> getFeedList(Integer catid){
        List<Meal> result = new ArrayList<Meal>();
        return result;
    }

    // 传入小猫id获取该小猫相关动态
    public List<Posting> getCatPostList(Integer catid)
    {
        List<Posting> result = new ArrayList<Posting>();
        return result;
    }

    // 传入用户id获取该用户发布的动态
    public List<Posting> getPostList(Integer userid)
    {
        List<Posting> result = new ArrayList<Posting>();
        return result;
    }

    // 根据传入参数添加Feed条目
    public Integer postFeed(Integer userid , Integer catid , String food){
        return 0;
    }

    // 根据传入参数添加Posting条目
    public Integer postPosting(Integer userid , Integer catid , String content){
        return 0;
    }

    // 传入帖子id修改帖子图片，返回值为图片imageurl
    public String setUPostImage(Integer postid , MultipartFile files){
        // 根据id获取帖子信息
        Posting tmp = postMapper.getPostId(postid);
        // 获取原先文件名称
        String filename = files.getOriginalFilename();
        // 设置在服务器存储位置
        String url = "D:\\Files\\Programing\\BJUTcats\\image\\storage\\posts\\" + tmp.getUserid().toString() + tmp.getCatid().toString() + new Date().getTime() + "\\" + filename;
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

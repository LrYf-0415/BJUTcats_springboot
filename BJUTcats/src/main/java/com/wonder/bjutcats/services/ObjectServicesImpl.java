package com.wonder.bjutcats.services;

import com.wonder.bjutcats.mapper.UserMapper;
import com.wonder.bjutcats.pojo.Cat;
import com.wonder.bjutcats.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ObjectServicesImpl implements ObjectServices{

    @Autowired
    private UserMapper userMapper;

    // 返回符合id(校区id)的小猫对象信息
    public List<Cat> getCatList(Integer campusid){
        List<Cat> result = new ArrayList<Cat>();
        return result;
    }

    // 根据小猫id获取小猫对象
    public Cat getCat(Integer catid){
        Cat result = new Cat();
        return result;
    }

    // 传入id修改用户头像，返回值为用户imageurl
    public String setUserImage(Integer userid , MultipartFile files){
        // 根据id获取用户信息
        User tmp = getUserInfo(userid);
        // 获取原先文件名称
        String filename = files.getOriginalFilename();
        // 设置在服务器存储位置
        String url = "D:\\Files\\Programing\\BJUTcats\\image\\storage\\user\\" + tmp.getUsername().toString() + "\\" + filename;
        // 存储在数据库中的url
        String url_db = "/api/storage/image/user/" + tmp.getUsername().toString() + "/" + filename;
        // 指定图片存放位置，若目标路径存在图像则覆盖(更新头像)
        File file = new File(url);
        if(!file.exists()){
            file.mkdirs();
        }
        try{
            // 存储图片
            files.transferTo(file);
            userMapper.setUserImageUrl(tmp.getId() , url_db);
        } catch(IOException e){
            e.printStackTrace();
        }

        // 返回图片位置
        return url_db;
    }

    // 传入id搜索该id的所有信息
    public User getUserInfo(Integer userid){
        User result = userMapper.getUserInfo(userid);

        return result;
    }

}

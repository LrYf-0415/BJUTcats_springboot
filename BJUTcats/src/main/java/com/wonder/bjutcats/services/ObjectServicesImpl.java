package com.wonder.bjutcats.services;

import com.wonder.bjutcats.mapper.CatMapper;
import com.wonder.bjutcats.mapper.FeedMapper;
import com.wonder.bjutcats.mapper.UserMapper;
import com.wonder.bjutcats.pojo.Cat;
import com.wonder.bjutcats.pojo.User;
import com.wonder.bjutcats.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ObjectServicesImpl implements ObjectServices{

    @Autowired
    private CatMapper catMapper;
    @Autowired
    private UserMapper userMapper;

    // 处理GET请求部分
    // 传入小猫id获取小猫对象
    public Cat getCat(Integer catid){
        try{
            Cat result = catMapper.getCatById(catid);
            return result;
        } catch(Exception e){
            log.info("error happend: " , e);
            return new Cat();
        }
    }

    // 传入用户id获取用户对象
    public User getUserInfo(String userid){
        try{
            User result = userMapper.getUserInfo(userid);
            if(result == null){
                return new User();
            }
            return result;
        } catch(Exception e){
            log.info("error happend: " , e);
            return new User();
        }
    }

    // 返回符合id(校区id)的小猫对象信息
    public List<Cat> getCatList(Integer campusid){
        try{
            List<Cat> result = catMapper.getCatSortCampus(campusid);
            return result;
        } catch(Exception e){
            log.info("error happend: " , e);
            return new ArrayList<Cat>();
        }
    }

    // 处理POST请求部分
    // 插入一条用户记录
    public Integer insertUserInfo(String userid , String username , Integer gender , String emails , String phone , String sessionkey){
        try{
            userMapper.newUserInfo(userid , username , gender , emails , phone , sessionkey);
            return 0;
        } catch(Exception e){
            log.info("error happend: " , e);
            return 1;
        }
    }

    // 根据id修改用户token
    public Integer updateUserToken(String userid , String sessionkey){
        try{
            userMapper.updateUserToken(userid , sessionkey);
            return 0;
        } catch(Exception e){
            log.info("error happened: " , e);
            return 1;
        }
    }

    // 根据id修改用户记录
    public Integer updateUserInfo(String userid , String username , Integer gender , String emails , String phone){
        try{
            userMapper.setUserInfo(userid , username , gender , emails , phone);
            return 0;
        } catch(Exception e){
            log.info("error happend: " , e);
            return 1;
        }
    }

    // 传入id修改用户头像，返回值为用户imageurl
    public String setUserImage(String userid , MultipartFile files){
        // 根据id获取用户信息
        User tmp = getUserInfo(userid);
        // 获取原先文件名称
        String filename = files.getOriginalFilename();
        // 设置在服务器存储位置
        String url = "D:/Files/Programing/BJUTcats/storage/image/user/" + tmp.getUsername().toString() + "/" + filename;
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
            log.info("error happend: " , e);
        }
        // 返回图片位置
        return url_db;
    }

    // 传入小猫id修改小猫照片，返回值为小猫caturl
    public String setCatImage(Integer catid , MultipartFile files){
        // 根据id获取小猫信息
        Cat tmp = getCat(catid);
        // 获取原先文件名称
        String filename = files.getOriginalFilename();
        // 设置在服务器存储位置
        String url = "D:/Files/Programing/BJUTcats/storage/image/cat/" + tmp.getName().toString() + "/" + filename;
        // 编写存储在数据库中的url
        String url_db = "/api/storage/image/cat/" + tmp.getName().toString() + "/" + filename;
        // 将图片存储，若目标路径存在则覆盖
        File file = new File(url);
        if(!file.exists()){
            file.mkdirs();
        }
        try{
            // 存储图片
            files.transferTo(file);
            catMapper.setCatImageUrl(tmp.getId() , url_db);
        } catch(IOException e){
            log.info("error happend: " , e);
        }
        // 返回图片url
        return url_db;
    }

}

package com.wonder.bjutcats.services;

import com.wonder.bjutcats.pojo.Cat;
import com.wonder.bjutcats.pojo.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ObjectServices {

    // 处理GET请求部分
    // 返回符合id(校区id)的小猫对象信息
    public List<Cat> getCatList(Integer campusid);

    // 根据小猫id获取小猫对象
    public Cat getCat(Integer catid);

    // 传入id搜索该id的所有信息
    public User getUserInfo(Integer userid);

    // 处理POST请求部分
    // 插入一条用户记录
    public Integer insertUserInfo(String username , Integer gender , String emails , String phone);

    // 根据id修改用户记录
    public Integer updateUserInfo(Integer userid , String username , Integer gender , String emails , String phone);

    // 传入用户id修改用户头像，返回值为用户imageurl
    public String setUserImage(Integer userid , MultipartFile files);

    // 传入小猫id修改小猫照片，返回值为小猫imageurl
    public String setCatImage(Integer catid , MultipartFile files);

}

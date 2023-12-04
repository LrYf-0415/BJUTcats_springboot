package com.wonder.bjutcats.services;

import com.wonder.bjutcats.pojo.Cat;
import com.wonder.bjutcats.pojo.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ObjectServices {

    // 返回符合id(校区id)的小猫对象信息
    public List<Cat> getCatList(Integer campusid);

    // 根据小猫id获取小猫对象
    public Cat getCat(Integer catid);

    // 传入id修改用户头像，返回值为用户imageurl
    public String setUserImage(Integer userid , MultipartFile files);

    // 传入id搜索该id的所有信息
    public User getUserInfo(Integer userid);

}

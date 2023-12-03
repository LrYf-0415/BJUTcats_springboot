package com.wonder.bjutcats.services;

import com.wonder.bjutcats.pojo.Cat;

import java.util.List;

public interface ObjectServices {

    // 返回符合id(校区id)的小猫对象信息
    public List<Cat> getCatList(Integer campusid);

    // 根据小猫id获取小猫对象
    public Cat getCat(Integer catid);

}

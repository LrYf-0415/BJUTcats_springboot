package com.wonder.bjutcats.services;

import com.wonder.bjutcats.pojo.Meal;
import com.wonder.bjutcats.pojo.Posting;

import java.util.List;

public interface ForumServices {

    // 传入小猫id获取该小猫的投喂记录
    public List<Meal> getFeedList(Integer catid);

    // 传入小猫id获取该小猫相关动态
    public List<Posting> getCatPostList(Integer catid);

    // 传入用户id获取该用户发布的动态
    public List<Posting> getPostList(Integer userid);

    // 根据传入参数添加Feed条目
    public Integer postFeed(Integer userid , Integer catid , String food);

    // 根据传入参数添加Posting条目
    public Integer postPosting(Integer userid , Integer catid , String content);

}

package com.wonder.bjutcats.services;

import com.wonder.bjutcats.mapper.ThumbMapper;
import com.wonder.bjutcats.pojo.Meal;
import com.wonder.bjutcats.pojo.Posting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ForumServicesImpl implements ForumServices {

    @Autowired
    private ThumbMapper tm;

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

}

package com.wonder.bjutcats.mapper;

import com.wonder.bjutcats.pojo.Posting;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface PostMapper {

    @Select("select id , userid , catid , content , imageurl from posting where catid = #{catid}")
    public List<Posting> getPostCat(Integer catid);

    @Select("select id , userid , catid , content , imageurl from posting where userid = #{userid}")
    public List<Posting> getPostUser(String userid);

    @Select("select id , userid , catid , content , imageurl from posting")
    public List<Posting> getAllPost();

    // 传入帖子id修改帖子图片链接
    @Update("update posting set imageurl = #{imageurl} where id = #{postid}")
    public Integer setUserImageUrl(Integer postid , String imageurl);

    // 根据id选择帖子
    @Select("select id , userid , catid , content , imageurl from posting")
    public Posting getPostId(Integer id);

    // 下列函数使用xml文件实现
    // 传入userid，catid，content插入新条目
    public Integer insertPosting(String userid , Integer catid , String content);

}

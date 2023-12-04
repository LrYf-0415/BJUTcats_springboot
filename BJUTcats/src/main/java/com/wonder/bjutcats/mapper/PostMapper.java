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
    public List<Posting> getPostUser(Integer userid);

    @Select("select id , userid , catid , content , imageurl from posting")
    public List<Posting> getAllPost();

    // 传入userid，catid，content插入新条目
    @Insert("insert into posting(userid , catid , content)" + "values (#{userid} , #{catid} , #{content})")
    public Integer insertPosting(Integer userid , Integer catid , String content);

    // 传入帖子id修改帖子图片链接
    @Update("update posting set imageurl = #{imageurl} where id = #{postid}")
    public Integer setUserImageUrl(Integer postid , String imageurl);

    // 根据id选择帖子
    @Select("select id , userid , catid , content , imageurl from posting")
    public Posting getPostId(Integer id);


}

package com.wonder.bjutcats.mapper;

import com.wonder.bjutcats.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    // 传入id获取用户信息
    @Select("select id , username , gender , emails , phone , imageurl from user where id = #{id}")
    public User getUserInfo(Integer userid);

    // 传入id修改该用户的imageurl
    @Update("update user set imageurl = #{imageurl} where id = #{userid}")
    public Integer setUserImageUrl(Integer userid , String imageurl);

}

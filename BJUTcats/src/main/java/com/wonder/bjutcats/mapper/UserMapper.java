package com.wonder.bjutcats.mapper;

import com.wonder.bjutcats.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    // 传入id获取用户信息
    @Select("select id , username , gender , emails , phone , imageurl from user where id = #{id}")
    public User getUserInfo(Integer userid);

    // 插入一条新的用户信息
    @Insert("insert into user(username , gender , emails , phone)" + "values (#{username} , #{gender} , #{emails} , #{phone})")
    public void newUserInfo(String username , Integer gender , String emails , String phone);

    // 传入id修改用户信息
    @Update("update user set username = #{username} , gender = #{gender} , emails = #{emails} , phone = #{phone} where id = #{userid}")
    public Integer setUserInfo(Integer userid , String username , Integer gender , String emails , String phone);

    // 传入id修改该用户的imageurl
    @Update("update user set imageurl = #{imageurl} where id = #{userid}")
    public Integer setUserImageUrl(Integer userid , String imageurl);

}

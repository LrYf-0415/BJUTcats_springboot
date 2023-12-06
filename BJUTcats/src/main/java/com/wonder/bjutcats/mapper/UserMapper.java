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
    public User getUserInfo(String userid);

    // 插入一条新的用户信息
    @Insert("insert into user(id , username , gender , emails , phone , token)" + "values (#{id} , #{username} , #{gender} , #{emails} , #{phone} , #{token})")
    public void newUserInfo(String id , String username , Integer gender , String emails , String phone , String token);

    // 传入id更新用户的SessionKey
    @Update("update user set token = #{token} where id = #{id}")
    public Integer updateUserToken(String id , String token);

    // 传入id修改用户信息
    @Update("update user set username = #{username} , gender = #{gender} , emails = #{emails} , phone = #{phone} where id = #{userid}")
    public Integer setUserInfo(String userid , String username , Integer gender , String emails , String phone);

    // 传入id修改该用户的imageurl
    @Update("update user set imageurl = #{imageurl} where id = #{userid}")
    public Integer setUserImageUrl(String userid , String imageurl);

}

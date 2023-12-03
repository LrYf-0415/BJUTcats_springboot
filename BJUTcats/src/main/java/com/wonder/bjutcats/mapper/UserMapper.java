package com.wonder.bjutcats.mapper;

import com.wonder.bjutcats.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    // 传入id获取用户信息
    @Select("select id , username , gender , emails , phone from user where id = #{id}")
    public User getUserInfo(Integer userid);

}

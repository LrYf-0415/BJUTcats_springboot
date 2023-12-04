package com.wonder.bjutcats.mapper;

import com.wonder.bjutcats.pojo.Meal;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FeedMapper {

    // 传入小猫id查询记录
    @Select("select id , userid , catid , food from feed where catid = #{catid}")
    public List<Meal> getFeedCat(Integer catid);

    // 传入用户id查询记录
    @Select("select id , userid , catid , food from feed where userid = #{userid}")
    public List<Meal> getFeedUser(Integer userid);

    // 插入新记录
    @Insert("insert into feed(userid , catid , food)" + "values (#{userid} , #{catid} , #{food})")
    public Integer insertFeed(Integer userid , Integer catid , String food);

}

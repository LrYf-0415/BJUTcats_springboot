package com.wonder.bjutcats.mapper;

import com.wonder.bjutcats.pojo.Meal;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FeedMapper {

    @Select("select id , userid , catid , food from feed where catid = #{catid}")
    public List<Meal> getFeedCat(Integer catid);

    @Select("select id , userid , catid , food from feed where userid = #{userid}")
    public List<Meal> getFeedUser(Integer userid);

    @Insert("insert into feed(userid , catid , food)" + "values (#{id} , #{userid} , #{catid} , #{food})")
    public Integer insertFeed(Integer userid , Integer catid , String food);

}

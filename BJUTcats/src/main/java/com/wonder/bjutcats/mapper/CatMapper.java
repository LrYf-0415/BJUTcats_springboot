package com.wonder.bjutcats.mapper;

import com.wonder.bjutcats.pojo.Cat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CatMapper {

    @Select("select id , name , gender , color , status , campus , location , detail , imageurl from cats where id = #{catid}")
    public Cat getCatById(Integer catid);

    @Select("select id , name , gender , color , status , campus , location , detail , imageurl from cats where campus = #{campusid}")
    public List<Cat> getCatSortCampus(Integer campusid);

    // 传入id修改该小猫的imageurl
    @Update("update user set imageurl = #{imageurl} where id = #{catid}")
    public Integer setCatImageUrl(Integer catid , String imageurl);

}

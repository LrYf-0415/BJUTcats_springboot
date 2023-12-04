package com.wonder.bjutcats.mapper;

import com.wonder.bjutcats.pojo.Cat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CatMapper {

    @Select("select id , name , gender , color , status , campus , location , detail , imageurl from cats where id = #{catid}")
    public List<Cat> getCatById(Integer catid);

    @Select("select id , name , gender , color , status , campus , location , detail , imageurl from cats where campus = #{campusid}")
    public List<Cat> getCatSortCampus(Integer campusid);

}

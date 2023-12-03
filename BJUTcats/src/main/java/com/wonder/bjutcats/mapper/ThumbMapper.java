package com.wonder.bjutcats.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ThumbMapper {

    @Select("select count(*) from thumb where catid = #{catid}")
    public Integer countThumbCat(Integer catid);

    @Select("select count(*) from thumb where userid = #{userid}")
    public Integer countThumbUser(Integer userid);

}

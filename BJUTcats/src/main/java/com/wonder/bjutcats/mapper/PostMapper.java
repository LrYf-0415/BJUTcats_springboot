package com.wonder.bjutcats.mapper;

import com.wonder.bjutcats.pojo.Posting;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PostMapper {

    @Select("select id , userid , catid , content from posting where catid = #{catid}")
    public List<Posting> getPostCat(Integer catid);

    @Select("select id , userid , catid , content from posting where userid = #{userid}")
    public List<Posting> getPostUser(Integer userid);

    @Select("select id , userid , catid , content from posting")
    public List<Posting> getAllPost();

    @Insert("insert into posting(userid , catid , content)" + "values (#{userid} , #{catid} , #{content})")
    public Integer insertPosting(Integer userid , Integer catid , String content);


}

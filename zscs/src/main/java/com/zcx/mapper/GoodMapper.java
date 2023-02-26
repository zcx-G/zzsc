package com.zcx.mapper;


import com.zcx.pojo.Good;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface GoodMapper {

    @Select("select * from good")
    @ResultMap("goodResultMap")
    List<Good> list();

    @Select("select * from good where category_name = #{categoryName}")
    @ResultMap("goodResultMap")
    List<Good> categoryList(String categoryName);

    @Select("select * from good where id = #{id}")
    Good selectById(int id);

    List<Good> selectGoodPage(@Param("begin") int begin, @Param("size") int size, @Param("name") String name);

    int selectCount(String name);

    @Insert("insert into " +
            "good" +
            "(name,category_name,price,image,description,status,update_time,update_user) " +
            "values" +
            "(#{name},#{categoryName},#{price},#{image},#{description},#{status},#{updateTime},#{updateUser})")
    int add(Good good);

    @Update("update good " +
            "set name = #{name},category_name =#{categoryName},price=#{price},image=#{image},description=#{description},update_time = #{updateTime},update_user = #{updateUser} " +
            "where id =#{id}")
    int update(Good good);

    int delete(@Param("ids") int[] ids);

    int status(@Param("ids") int[] ids ,@Param("status") int status);
}

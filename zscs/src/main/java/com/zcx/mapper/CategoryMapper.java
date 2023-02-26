package com.zcx.mapper;


import com.zcx.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CategoryMapper {

    @ResultMap("categoryResultMap")
    @Select("select * from category limit #{begin},#{size}")
    List<Category> selectByPage(@Param("begin") int begin,@Param("size") int size);

    @Select("select count(*) from category")
    int selectTotalCount();

    @ResultMap("categoryResultMap")
    @Select("select * from category")
    List<Category> list();

    @Insert("insert into category" +
            "(name,sort,update_time,update_user) " +
            "values" +
            "(#{name},#{sort},#{updateTime},#{updateUser})")
    int add(Category category);

    @Update("update category " +
            "set " +
            "name = #{name},sort = #{sort}," +
            "update_time = #{updateTime},update_user = #{updateUser} " +
            "where id =#{id}")
    int update(Category category);

    @Delete("delete from category where id = #{id}")
    int delete(Long id);


}

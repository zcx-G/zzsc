package com.zcx.mapper;


import com.zcx.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CategoryMapper {

    @ResultMap("categoryResultMap")
    @Select("select * from category limit #{begin},#{size}")
    List<Category> selectByPage(@Param("begin") int begin,@Param("size") int size);
    //分页查询

    @Select("select count(*) from category")
    int selectTotalCount();
    //分类总数

    @ResultMap("categoryResultMap")
    @Select("select * from category")
    List<Category> list();
    //查询所有分类

    @Insert("insert into category" +
            "(name,sort,update_time,update_user) " +
            "values" +
            "(#{name},#{sort},#{updateTime},#{updateUser})")
    int add(Category category);
    //添加分类

    @Update("update category " +
            "set " +
            "name = #{name},sort = #{sort}," +
            "update_time = #{updateTime},update_user = #{updateUser} " +
            "where id =#{id}")
    int update(Category category);
    //修改分类

    @Delete("delete from category where id = #{id}")
    int delete(Long id);
    //删除分类

}

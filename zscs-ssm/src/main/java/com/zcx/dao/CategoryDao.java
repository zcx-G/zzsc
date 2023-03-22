package com.zcx.dao;


import com.zcx.pojo.Category;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface CategoryDao {

    @ResultMap("categoryResultMap")
    @Select("SELECT * FROM category LIMIT #{begin},#{size}")
    List<Category> selectByPage(@Param("begin") int begin,@Param("size") int size);
    //分页查询

    @Select("SELECT count(*) FROM category")
    int selectTotalCount();
    //分类总数

    @ResultMap("categoryResultMap")
    @Select("SELECT * FROM category")
    List<Category> list();
    //查询所有分类


    @ResultMap("categoryResultMap")
    @Select("SELECT * FROM category WHERE id = #{id}")
    Category selectById(Long id);
    //根据ID查询


    @Insert("INSERT INTO  category" +
            "(name,sort,update_time,update_user) " +
            "VALUES" +
            "(#{name},#{sort},#{updateTime},#{updateUser})")
    int add(Category category);
    //添加分类

    @Update("UPDATE category " +
            "SET " +
            "name = #{name},sort = #{sort}," +
            "update_time = #{updateTime},update_user = #{updateUser} " +
            "WHERE id =#{id}")
    int update(Category category);
    //修改分类

    @Delete("DELETE FROM category WHERE id = #{id}")
    int delete(Long id);
    //删除分类

}

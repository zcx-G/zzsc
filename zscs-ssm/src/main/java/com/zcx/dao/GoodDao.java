package com.zcx.dao;


import com.zcx.pojo.Good;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface GoodDao {

    @Select("SELECT * FROM good")
    @ResultMap("goodResultMap")
    List<Good> list();
    //查询所有商品

    @Select("SELECT * FROM good WHERE category_id = #{categoryId} AND status = 1")
    @ResultMap("goodResultMap")
    List<Good> categoryList(Long categoryId);
    //查询当前分类下的商品

    @Select("SELECT * FROM good WHERE id = #{id}")
    @ResultMap("goodResultMap")
    Good selectById(int id);
    //根据ID查询

    List<Good> selectGoodPage(@Param("begin") int begin, @Param("size") int size, @Param("name") String name);
    //分页条件查询

    int selectCount(String name);
    //商品数量

    @Insert("INSERT INTO " +
            "good" +
            "(name,category_name,category_id, price,sale_num,image,description,status,update_time,update_user) " +
            "VALUES " +
            "(#{name},#{categoryName},#{categoryId},#{price},0,#{image},#{description},#{status},#{updateTime},#{updateUser})")
    boolean add(Good good);
    //添加商品

    @Update("UPDATE good " +
            "SET " +
            "name = #{name},category_name =#{categoryName},category_id =#{categoryId}," +
            "price=#{price},image=#{image},description=#{description}," +
            "update_time = #{updateTime},update_user = #{updateUser} " +
            "WHERE id =#{id}")
    boolean update(Good good);
    //修改商品

    boolean delete(@Param("ids") int[] ids);
    //删除商品

    boolean status(@Param("ids") int[] ids ,@Param("status") int status);
    //修改商品状态

    @Update("UPDATE good SET sale_num=sale_num+#{num} WHERE id=#{id}")
    void addSaleNum(@Param("num") int num, @Param("id") int id);
    //销量增加
}

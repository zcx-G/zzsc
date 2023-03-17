package com.zcx.mapper;


import com.zcx.pojo.Good;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface GoodMapper {

    @Select("select * from good")
    @ResultMap("goodResultMap")
    List<Good> list();
    //查询所有商品

    @Select("select * from good where category_id = #{categoryId} AND status = 1")
    @ResultMap("goodResultMap")
    List<Good> categoryList(Long categoryId);
    //查询当前分类下的商品

    @Select("select * from good where id = #{id}")
    Good selectById(int id);
    //根据ID查询

    List<Good> selectGoodPage(@Param("begin") int begin, @Param("size") int size, @Param("name") String name);
    //分页条件查询

    int selectCount(String name);
    //商品数量

    @Insert("insert into " +
            "good" +
            "(name,category_name,category_id, price,sale_num,image,description,status,update_time,update_user) " +
            "values" +
            "(#{name},#{categoryName},#{categoryId},#{price},0,#{image},#{description},#{status},#{updateTime},#{updateUser})")
    int add(Good good);
    //添加商品

    @Update("update good " +
            "set name = #{name},category_name =#{categoryName},category_id =#{categoryId},price=#{price},image=#{image},description=#{description},update_time = #{updateTime},update_user = #{updateUser} " +
            "where id =#{id}")
    int update(Good good);
    //修改商品

    int delete(@Param("ids") int[] ids);
    //删除商品

    int status(@Param("ids") int[] ids ,@Param("status") int status);
    //修改商品状态

    @Update("update good set sale_num=sale_num+#{num} where id=#{id}")
    void addSaleNum(@Param("num") int num, @Param("id") int id);
    //销量增加
}

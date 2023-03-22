package com.zcx.dao;


import com.zcx.pojo.Root;
import com.zcx.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


public interface UserDao {


    /**
     * 根据电话号码查询用户对象
     */
    @Select("select * from user where phone = #{phone}")
    User select(String phone);

    /**
     * 根据Id查询用户对象
     */
    @Select("select * from user where id = #{userId}")
    User selectById(Long userId);

    /**
     * 添加新用户
     */
    @Insert("INSERT INTO `user`" +
            "(phone,status)" +
            "VALUES(#{phone},1)")
    boolean add(String phone);

    /**
     * 根据用户名查询管理员对象
     */
    @Select("select * from tb_root where username = #{username}")
    Root selectRoot(String username);

}

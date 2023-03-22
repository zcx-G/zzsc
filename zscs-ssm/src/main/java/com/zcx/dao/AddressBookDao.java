package com.zcx.dao;


import com.zcx.pojo.AddressBook;
import org.apache.ibatis.annotations.*;

import java.util.List;
public interface AddressBookDao {

    @Insert("INSERT INTO address_book(user_id, consignee, sex, phone, detail, label, is_default, update_time) " +
            "VALUE (#{userId},#{consignee},#{sex},#{phone},#{detail},#{label},0,#{updateTime})")
    boolean add(AddressBook addressBook);
    //添加地址

    @Insert("UPDATE address_book " +
            "SET user_id=#{userId}, consignee=#{consignee}, sex=#{sex}, phone=#{phone}, " +
                "detail=#{detail}, label=#{label}, is_default=#{isDefault}, update_time=#{updateTime} " +
            "WHERE id = #{id}")
    boolean update(AddressBook addressBook);
    //修改地址

    @Update("UPDATE address_book SET is_default=0 WHERE user_id=#{userId}")
    boolean updateDefault(Long userId);
    //根据用户ID将全部地址状态设为非默认

    @Update("UPDATE address_book SET is_default=1 WHERE id=#{id}")
    boolean updateDefaultById(Long id);
    //将当前ID设为默认地址

    @Select("SELECT * FROM address_book WHERE id = #{id}")
    @ResultMap("addressBookResultMap")
    AddressBook selectById(Long id);
    //根据Id查找地址

    @Delete("DELETE FROM address_book WHERE id = #{id}")
    boolean delete(Long id);
    //根据Id删除地址

    @Select("SELECT * FROM address_book WHERE is_default = 1 AND user_id = #{userId}")
    @ResultMap("addressBookResultMap")
    AddressBook selectDefaultAddress(Long userId);
    //查找用户默认地址

    @Select("SELECT * FROM address_book WHERE user_id = #{userId} ORDER BY update_time DESC")
    @ResultMap("addressBookResultMap")
    List<AddressBook> selectAddressList(Long userId);
    //查询当前用户所有地址
}

package com.zcx.mapper;


import com.zcx.pojo.AddressBook;
import org.apache.ibatis.annotations.*;

import java.util.List;
public interface AddressBookMapper {

    @Insert("INSERT INTO address_book(user_id, consignee, sex, phone, detail, label, is_default, update_time, update_user) " +
            "VALUE (#{userId},#{consignee},#{sex},#{phone},#{detail},#{label},0,#{updateTime},#{updateUser})")
    int add(AddressBook addressBook);

    @Update("UPDATE address_book SET is_default=0 WHERE user_id=#{userId}")
    int updateDefault(Long userId);

    @Update("UPDATE address_book SET is_default=1 WHERE id=#{id}")
    int updateDefaultById(Long id);

    @Select("SELECT * FROM address_book WHERE id = #{id}")
    @ResultMap("addressBookResultMap")
    AddressBook selectById(Long id);

    @Select("SELECT * FROM address_book WHERE is_default = 1 AND user_id = #{userId}")
    @ResultMap("addressBookResultMap")
    AddressBook selectDefaultAddress(Long userId);

    @Select("SELECT * FROM address_book WHERE user_id = #{userId} ORDER BY update_time DESC")
    @ResultMap("addressBookResultMap")
    List<AddressBook> selectAddressList(Long userId);
}

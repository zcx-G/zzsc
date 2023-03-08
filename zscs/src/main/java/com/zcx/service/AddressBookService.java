package com.zcx.service;


import com.zcx.pojo.AddressBook;

import java.util.List;

public interface AddressBookService {

    int add(AddressBook addressBook);  //添加地址

    int update(AddressBook addressBook);   //修改地址

    void updateDefault(Long userId); //根据用户ID将全部地址状态设为非默认

    int updateDefaultById(Long id);  //将当前ID设为默认地址

    AddressBook selectById(Long id);  //根据Id查找地址

    AddressBook selectDefaultAddress(Long userId); //查找用户默认地址

    List<AddressBook> selectAddressList(Long userId);  //查询当前用户所有地址

    int delete(Long id);  //根据Id删除地址

}

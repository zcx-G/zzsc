package com.zcx.service;


import com.zcx.pojo.AddressBook;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface AddressBookService {

    boolean add(AddressBook addressBook);              //添加地址

    boolean update(AddressBook addressBook);           //修改地址

    boolean updateDefault(Long userId);                //根据用户ID将全部地址状态设为非默认

    boolean updateDefaultById(Long id);                //将当前ID设为默认地址

    AddressBook selectById(Long id);                   //根据Id查找地址

    boolean delete(Long id);                           //根据Id删除地址

    AddressBook selectDefaultAddress(Long userId);     //查找用户默认地址

    List<AddressBook> selectAddressList(Long userId);  //查询当前用户所有地址



}

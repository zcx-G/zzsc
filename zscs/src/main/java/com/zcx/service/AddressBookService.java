package com.zcx.service;


import com.zcx.pojo.AddressBook;

import java.util.List;

public interface AddressBookService {

    int add(AddressBook addressBook);

    int updateDefault(Long userId);

    int updateDefaultById(Long id);

    AddressBook selectById(Long id);

    AddressBook selectDefaultAddress(Long userId);

    List<AddressBook> selectAddressList(Long userId);
}

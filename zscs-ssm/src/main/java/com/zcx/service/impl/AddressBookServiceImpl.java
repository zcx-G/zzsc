package com.zcx.service.impl;


import com.zcx.dao.AddressBookDao;
import com.zcx.pojo.AddressBook;
import com.zcx.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressBookServiceImpl implements AddressBookService {
    @Autowired
    private AddressBookDao addressBookDao;

    @Override  //添加地址
    public boolean add(AddressBook addressBook) {
        return addressBookDao.add(addressBook);
    }

    @Override  //修改地址
    public boolean update(AddressBook addressBook) {
        return addressBookDao.update(addressBook);
    }

    @Override  //根据用户ID将全部地址状态设为非默认
    public boolean updateDefault(Long userId) {
        return addressBookDao.updateDefault(userId);
    }

    @Override  //将当前ID设为默认地址
    public boolean updateDefaultById(Long id) {
        return addressBookDao.updateDefaultById(id);
    }

    @Override //根据Id查找地址
    public AddressBook selectById(Long id) {
        return addressBookDao.selectById(id);
    }

    @Override  //根据Id删除地址
    public boolean delete(Long id) {
        return addressBookDao.delete(id);
    }

    @Override  //查找用户默认地址
    public AddressBook selectDefaultAddress(Long userId) {
        return addressBookDao.selectDefaultAddress(userId);
    }

    @Override  //查询当前用户所有地址
    public List<AddressBook> selectAddressList(Long userId) {
        return addressBookDao.selectAddressList(userId);
    }
}

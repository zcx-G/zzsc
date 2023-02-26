package com.zcx.service.impl;



import com.zcx.mapper.AddressBookMapper;
import com.zcx.pojo.AddressBook;
import com.zcx.service.AddressBookService;
import com.zcx.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class AddressBookServiceImpl implements AddressBookService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public int add(AddressBook addressBook) {
        //获取SQLSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper
        AddressBookMapper mapper = sqlSession.getMapper(AddressBookMapper.class);

        int i = mapper.add(addressBook);
        sqlSession.commit();//提交事务
        sqlSession.close();//释放资源
        return i;
    }

    @Override
    public int updateDefault(Long userId) {
        //获取SQLSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper
        AddressBookMapper mapper = sqlSession.getMapper(AddressBookMapper.class);

        int i = mapper.updateDefault(userId);
        sqlSession.commit();//提交事务
        sqlSession.close();//释放资源
        return i;
    }

    @Override
    public int updateDefaultById(Long id) {
        //获取SQLSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper
        AddressBookMapper mapper = sqlSession.getMapper(AddressBookMapper.class);

        int i = mapper.updateDefaultById(id);
        sqlSession.commit();//提交事务
        sqlSession.close();//释放资源
        return i;
    }

    @Override
    public AddressBook selectById(Long id) {
        //获取SQLSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper
        AddressBookMapper mapper = sqlSession.getMapper(AddressBookMapper.class);

        AddressBook addressBook = mapper.selectById(id);
        sqlSession.close();//释放资源
        return addressBook;
    }

    @Override
    public AddressBook selectDefaultAddress(Long userId) {
        //获取SQLSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper
        AddressBookMapper mapper = sqlSession.getMapper(AddressBookMapper.class);

        AddressBook addressBook = mapper.selectDefaultAddress(userId);
        sqlSession.close();//释放资源
        return addressBook;
    }

    @Override
    public List<AddressBook> selectAddressList(Long userId) {
        //获取SQLSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper
        AddressBookMapper mapper = sqlSession.getMapper(AddressBookMapper.class);

        List<AddressBook> addressBooks = mapper.selectAddressList(userId);
        sqlSession.commit();
        sqlSession.close();
        return addressBooks;
    }
}

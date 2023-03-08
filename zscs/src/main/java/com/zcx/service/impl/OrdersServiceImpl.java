package com.zcx.service.impl;

import com.zcx.mapper.*;
import com.zcx.pojo.*;
import com.zcx.service.OrdersService;
import com.zcx.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


public class OrdersServiceImpl implements OrdersService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override //订单提交
    public int submit(Orders orders) {
        //获取SQLSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper
        OrdersMapper ordersMapper = sqlSession.getMapper(OrdersMapper.class);
        ShoppingCartMapper shoppingCartMapper = sqlSession.getMapper(ShoppingCartMapper.class);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        AddressBookMapper addressBookMapper = sqlSession.getMapper(AddressBookMapper.class);
        GoodMapper goodMapper = sqlSession.getMapper(GoodMapper.class);

        //获取购物车，用户，地址信息
        List<ShoppingCart> shoppingCarts = shoppingCartMapper.list(orders.getUserId());
        User user = userMapper.selectById(orders.getUserId());
        AddressBook addressBook = addressBookMapper.selectById(orders.getAddressBookId());

        //订单号
        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        Long orderId = Long.valueOf(time+orders.getUserId());

        //订单详情
        AtomicInteger amount = new AtomicInteger(0);
        List<OrderDetail> orderDetails = shoppingCarts.stream().map((item) ->{
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(orderId);
            orderDetail.setNumber(item.getNumber());
            orderDetail.setGoodId(item.getGoodId());
            goodMapper.addSaleNum(item.getNumber(),item.getGoodId());
            orderDetail.setName(item.getName());
            orderDetail.setImage(item.getImage());
            orderDetail.setAmount(item.getAmount());
            amount.addAndGet(item.getAmount().multiply(new BigDecimal(item.getNumber())).intValue());
            return orderDetail;
        }).collect(Collectors.toList());

        //订单及保存
        orders.setId(orderId);
        orders.setNumber(String.valueOf(orderId));
        orders.setOrderTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        orders.setStatus(2);
        orders.setUserName(user.getName());
        orders.setConsignee(addressBook.getConsignee());
        orders.setPhone(addressBook.getPhone());
        orders.setAddress(addressBook.getDetail());
        orders.setAmount(new BigDecimal(amount.get()));
        int add = ordersMapper.add(orders);

        //订单详情及保存
        System.out.println(orderDetails);
        ordersMapper.addAll(orderDetails);

        //清空购物车
        shoppingCartMapper.deleteAll(orders.getUserId());

        sqlSession.commit();
        sqlSession.close();
        return add;
    }

    @Override  //分页查询
    public Return<PageBean> selectOrderPage(Long userId, int page, int pageSize) {
        //获取SQLSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper
        OrdersMapper mapper = sqlSession.getMapper(OrdersMapper.class);

        int begin = (page-1) * pageSize;
        int size = pageSize;

        List<Orders> orders =  mapper.selectOrderPage(userId, begin, size);
        int count = mapper.selectOrderCount(userId);

        System.out.println(orders);
        PageBean<Orders> pageBean = new PageBean<>();
        pageBean.setCount(count);
        pageBean.setRows(orders);
        sqlSession.close();
        return Return.success(pageBean);
    }

    @Override  //查询所有
    public Return<PageBean> selectAllPage(int page, int pageSize , String orderId) {
        //获取SQLSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper
        OrdersMapper mapper = sqlSession.getMapper(OrdersMapper.class);

        int begin = (page-1) * pageSize;
        int size = pageSize;
        if (orderId!=null && orderId!="")
            orderId = "%"+orderId+"%";

        List<Orders> orders =  mapper.selectAllPage( begin, size,orderId);
        int count = mapper.selectCount();

        System.out.println(orders);
        PageBean<Orders> pageBean = new PageBean<>();
        pageBean.setCount(count);
        pageBean.setRows(orders);
        sqlSession.close();
        return Return.success(pageBean);
    }

    @Override  //修改状态
    public int updateStatus(Long id, String status) {
        //获取SQLSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper
        OrdersMapper mapper = sqlSession.getMapper(OrdersMapper.class);

        int i = mapper.updateStatus(id, status);
        sqlSession.commit();
        sqlSession.close();
        return i;

    }

    @Override  //根据订单ID查询订单详情
    public List<OrderDetail> selectByOrderId(Long orderId) {
        //获取SQLSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper
        OrdersMapper mapper = sqlSession.getMapper(OrdersMapper.class);

        return mapper.selectByOrderId(orderId);
    }
}

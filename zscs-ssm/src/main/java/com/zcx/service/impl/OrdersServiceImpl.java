package com.zcx.service.impl;

import com.zcx.dao.*;
import com.zcx.pojo.*;
import com.zcx.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersDao ordersDao;
    @Autowired
    private ShoppingCartDao shoppingCartDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private AddressBookDao addressBookDao;
    @Autowired
    private GoodDao goodDao;
    
    @Override //订单提交
    public boolean submit(Orders orders) {

        //获取购物车，用户，地址信息
        List<ShoppingCart> shoppingCarts = shoppingCartDao.list(orders.getUserId());
        User user = userDao.selectById(orders.getUserId());
        AddressBook addressBook = addressBookDao.selectById(orders.getAddressBookId());

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
            goodDao.addSaleNum(item.getNumber(),item.getGoodId());
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
        boolean add = ordersDao.add(orders);

        //订单详情及保存
        System.out.println(orderDetails);
        ordersDao.addAll(orderDetails);

        //清空购物车
        shoppingCartDao.deleteAll(orders.getUserId());

        return add;
    }

    @Override  //分页查询
    public PageBean<Orders> selectOrderPage(Long userId, int page, int pageSize) {
        int begin = (page-1) * pageSize;
        int size = pageSize;

        List<Orders> orders =  ordersDao.selectOrderPage(userId, begin, size);
        int count = ordersDao.selectOrderCount(userId);

        System.out.println(orders);
        PageBean<Orders> pageBean = new PageBean<>();
        pageBean.setCount(count);
        pageBean.setRows(orders);
        return pageBean;
    }

    @Override  //查询所有
    public PageBean<Orders> selectAllPage(int page, int pageSize , String orderId) {
        int begin = (page-1) * pageSize;
        int size = pageSize;
        if (orderId!=null && orderId!="")
            orderId = "%"+orderId+"%";

        List<Orders> orders =  ordersDao.selectAllPage( begin, size,orderId);
        int count = ordersDao.selectCount();

        System.out.println(orders);
        PageBean<Orders> pageBean = new PageBean<>();
        pageBean.setCount(count);
        pageBean.setRows(orders);
        return pageBean;
    }

    @Override  //修改状态
    public boolean updateStatus(Long id, String status) {;
        return ordersDao.updateStatus(id, status);

    }

    @Override  //根据订单ID查询订单详情
    public List<OrderDetail> selectByOrderId(Long orderId) {
        return ordersDao.selectByOrderId(orderId);
    }
}

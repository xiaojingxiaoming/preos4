package com.kgc.service.impl;

import com.github.pagehelper.PageHelper;
import com.kgc.dao.IOrdersDao;
import com.kgc.domain.Orders;
import com.kgc.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class OrdersServiceImpl implements IOrdersService{
   @Autowired
    private IOrdersDao dao;

    //分页
    @Override
    public List<Orders> findAll(Integer page, Integer size) throws Exception {
        PageHelper.startPage(page,size);
        return dao.findAll();
    }

    @Override
    public Orders findById(String ordersId) {
       return dao.findById(ordersId);

    }

    @Override
    public void updateOrder(Orders orders) {
        dao.updateOrder(orders);
    }
    //未分页
   /* @Override
    public List<Orders> findAll() throws Exception {
        //参数pageNum表示当前页，pageSize表示每页显示条数
        PageHelper.startPage(1,5);
        return dao.findAll();
    }*/
}

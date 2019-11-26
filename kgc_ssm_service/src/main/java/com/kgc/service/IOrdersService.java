package com.kgc.service;

import com.kgc.domain.Orders;

import java.util.List;

public interface IOrdersService {
    //未分页
   // List<Orders> findAll() throws Exception;

    //分页
    List<Orders> findAll(Integer page,Integer size) throws Exception;

    //根据id查询订单详情信息
    Orders findById(String ordersId) throws Exception;

    void updateOrder(Orders orders);
}

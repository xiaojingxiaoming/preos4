package com.kgc.service;

import com.kgc.domain.Product;

import java.util.List;

public interface IProductService {

    //List<Product> findAll() throws Exception;
    List<Product> findAll(int page,int size,String name) throws Exception;

    void save(Product product);

    void updateStutas(String[]  ids) throws Exception;


    Product findById(String id) throws Exception;
    //修改订单
    void updateProduct(Product product);
    //根据ids删除产品
    void deleteByIds(String[] ids);
}

package com.kgc.service.impl;

import com.github.pagehelper.PageHelper;
import com.kgc.dao.IProductDao;
import com.kgc.domain.Product;
import com.kgc.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements IProductService{
    @Autowired
    private IProductDao dao;
    @Override
    public List<Product> findAll(int page, int size,String name) throws Exception {
        PageHelper.startPage(page,size);
        return dao.findAll(name);
    }

    @Override
    public void save(Product product) {
        dao.save(product);
    }

    @Override
    public void updateStutas( String[] ids) throws Exception {
       dao.updateStatus(ids);
    }

    @Override
    public Product findById(String id) throws Exception {
        return dao.findById(id);
    }

    @Override
    public void updateProduct(Product product) {
        //String id=product.getId();
        dao.updateProduct(product);
    }

    @Override
    public void deleteByIds(String[] ids) {
        dao.deleteByIds(ids);
    }
}

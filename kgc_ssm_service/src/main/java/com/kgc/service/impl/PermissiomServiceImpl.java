package com.kgc.service.impl;

import com.github.pagehelper.PageHelper;
import com.kgc.dao.IPermissionDao;
import com.kgc.domain.Permission;
import com.kgc.service.IPermissiomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PermissiomServiceImpl implements IPermissiomService{
    @Autowired
    private IPermissionDao permissionDao;
    @Override
    public List<Permission> findAll(int page,int size) throws Exception {
        PageHelper.startPage(page,size);
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) throws Exception {
        permissionDao.save(permission);
    }

    @Override
    public Permission findById(String id) {
        return permissionDao.findById(id);
    }

    @Override
    public void deleteById(String id) {
        permissionDao.deleteById(id);
    }
}

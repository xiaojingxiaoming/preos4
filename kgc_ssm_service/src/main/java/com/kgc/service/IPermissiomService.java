package com.kgc.service;

import com.kgc.domain.Permission;

import java.util.List;

public interface IPermissiomService {
    List<Permission> findAll(int page,int size) throws Exception;

    void save(Permission permission) throws Exception;

    Permission findById(String id);

    void deleteById(String id);
}

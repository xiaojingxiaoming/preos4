package com.kgc.service;

import com.kgc.domain.SysLog;

import java.util.List;

public interface ISysLogService {

    void save(SysLog sysLog) throws Exception;

    List<SysLog> findAll(int page,int size,String search) throws Exception;

    void deleteById(String[] ids);
}

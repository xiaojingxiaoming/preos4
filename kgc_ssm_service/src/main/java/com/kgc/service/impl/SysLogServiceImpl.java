package com.kgc.service.impl;

import com.github.pagehelper.PageHelper;
import com.kgc.dao.ISysLogDao;
import com.kgc.domain.SysLog;
import com.kgc.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysLogServiceImpl implements ISysLogService{
    @Autowired
    private ISysLogDao sysLogDao;
    @Override
    public void save(SysLog sysLog) throws Exception {
        sysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll(int page,int size,String search) throws Exception {
        PageHelper.startPage(page,size);
        return sysLogDao.findAll(search);
    }

    @Override
    public void deleteById(String[] ids) {
        sysLogDao.deleteById(ids);
    }
}

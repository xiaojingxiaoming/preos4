package com.kgc.service.impl;

import com.github.pagehelper.PageHelper;
import com.kgc.dao.IUserDao;
import com.kgc.domain.Role;
import com.kgc.domain.UserInfo;
import com.kgc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserService implements IUserService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private IUserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo=null;
        try {
            userInfo= userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //把userInfo封装成UserDetails
       // User user=new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),getAuthority(userInfo.getRoles()));
        User user=new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus()==0?false:true,true,true,true,getAuthority(userInfo.getRoles()));
        return user;
    }

    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> list=new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));

        }
        return list;
    }

    @Override
    public List<UserInfo> findAll(int page,int size) throws Exception {
        PageHelper.startPage(page,size);
        return userDao.findAll();
    }

    @Override
    public void save(UserInfo userInfo) {
        //需要在service中对密码进行加密 然后再传递给dao
        //MD5  不严格 被人破解了  北大的女教授
       String encode= bCryptPasswordEncoder.encode(userInfo.getPassword());
        userInfo.setPassword(encode);
        userDao.save(userInfo);
    }

    @Override
    public UserInfo findById(String id) throws Exception {
        return userDao.findById(id);
    }

    @Override
    public List<Role> findOtherRoles(String userId) throws Exception {
        return userDao.findOtherRoles(userId);
    }

    @Override
    public void addRoleToUser(String userId, String[] roleIds) throws Exception {
        //调用dao完成用户添加角色的插入
        for (String roleId : roleIds) {
            userDao.addRoleToUser(userId,roleId);
        }

    }
}

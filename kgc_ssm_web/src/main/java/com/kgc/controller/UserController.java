package com.kgc.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.domain.Role;
import com.kgc.domain.UserInfo;
import com.kgc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService uservice;
    @RequestMapping("/findAll")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,@RequestParam(name = "size",required = true,defaultValue = "4")Integer size) throws Exception {
        ModelAndView mv =new ModelAndView();
        List<UserInfo> userList=uservice.findAll(page,size);
        PageInfo pageInfo=new PageInfo(userList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("user-list");
        return mv;
    }
    @RequestMapping("/save")
    @PreAuthorize("authentication.principal.username=='tom'")
    public String save(UserInfo userInfo){
        uservice.save(userInfo);
        return "redirect:findAll.do";
    }
    @RequestMapping("/findById")
    public ModelAndView findById(String id) throws Exception {
        ModelAndView mv =new ModelAndView();
        UserInfo userInfo=uservice.findById(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;
    }
    //查询用户以及用户可以添加的角色
    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) String userId) throws Exception {
        //1.
        UserInfo userInfo = uservice.findById(userId);
        List<Role> roles=uservice.findOtherRoles(userId);
        ModelAndView mv=new ModelAndView();
        mv.addObject("roleList",roles);
        mv.addObject("user",userInfo);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(@RequestParam(name = "userId",required = true) String userId,@RequestParam(name = "ids",required = true) String[] roleIds) throws Exception {
        uservice.addRoleToUser(userId,roleIds);
        return "redirect:findAll.do";
    }
}

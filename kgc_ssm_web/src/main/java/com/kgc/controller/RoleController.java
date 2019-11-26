package com.kgc.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.domain.Permission;
import com.kgc.domain.Role;
import com.kgc.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page, @RequestParam(name = "size",required = true,defaultValue = "4")Integer size) throws Exception {
        ModelAndView mv=new ModelAndView();
        List<Role> roleList = roleService.findAll(page,size);
        PageInfo pageInfo=new PageInfo(roleList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/save")
    public String save(Role role) throws Exception {
        roleService.save(role);
        return "redirect:findAll.do";
    }

    @RequestMapping("findById")
    public ModelAndView findById(String id) throws Exception {
        ModelAndView mv=new ModelAndView();
        Role role=roleService.findById(id);
        System.out.println("role==="+role);
        mv.addObject("role",role);
        mv.setViewName("role-show");
        return mv;
    }
    @RequestMapping("/deleteById")
    public String deleteById(String id){
        roleService.deleteById(id);
        return "redirect:findAll.do";
    }
    @RequestMapping("/findRoleByIdAndAllPermission")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id",required = true)String roleId) throws Exception {
       Role role= roleService.findById(roleId);
       List<Permission> otherPermission=roleService.findOtherPermission(roleId);
        ModelAndView mv=new ModelAndView();
        mv.addObject("role",role);
        mv.addObject("permissionList",otherPermission);
        mv.setViewName("role-permission-add");
        return mv;
    }
    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(@RequestParam(name = "roleId",required = true) String roleId,@RequestParam(name = "permissionIds",required = true) String[] permissionIds){
        roleService.addPermissionToRole(roleId,permissionIds);
        return "redirect:findAll.do";
    }
}

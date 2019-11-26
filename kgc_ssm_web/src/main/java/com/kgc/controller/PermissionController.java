package com.kgc.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.domain.Permission;
import com.kgc.service.IPermissiomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private IPermissiomService permissiomService;
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page, @RequestParam(name = "size",required = true,defaultValue = "4")Integer size) throws Exception {
        ModelAndView mv=new ModelAndView();
        List<Permission> permissionList = permissiomService.findAll(page,size);
        PageInfo pageInfo=new PageInfo(permissionList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("permission-list");
        return mv;
    }
    @RequestMapping("/save")
    public String save(Permission permission) throws Exception {
        permissiomService.save(permission);
        return "redirect:findAll.do";
    }
    @RequestMapping("findById")
    public ModelAndView findById(String id){
        ModelAndView mv=new ModelAndView();
        Permission permission=permissiomService.findById(id);
        mv.addObject("permission",permission);
        mv.setViewName("permission-show");
        return mv;
    }

    @RequestMapping("deleteById")
    public String deleteById(String id){
        permissiomService.deleteById(id);
        return "redirect:findAll.do";
    }
}

package com.kgc.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.domain.Product;
import com.kgc.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService service;
    @RequestMapping("/findAll")
    @RolesAllowed("ADMIN")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,
                                @RequestParam(name = "size",required = true,defaultValue = "4")Integer size,
                                @RequestParam(name = "name",required = false,defaultValue = "")String name) throws Exception {
        ModelAndView mv=new ModelAndView();
        List<Product> ps=service.findAll(page,size,name);
        PageInfo pageInfo=new PageInfo(ps);
        mv.addObject("pageInfo",pageInfo);
        mv.addObject("name",name);
        mv.setViewName("product-pages-list");
        return mv;
    }
    @RequestMapping("/save")
    public String save(Product product){
        service.save(product);
        return "redirect:findAll.do";
    }
    @RequestMapping("/updateStatus")
    public String updateStatus(@RequestParam(name = "ids") String[] ids) throws Exception {
        if(ids!=null){
            service.updateStutas(ids);
        }
        return "redirect:findAll.do";
    }
    @RequestMapping("/showById")
    public ModelAndView showById(@RequestParam(name = "id") String id) throws Exception {
        ModelAndView mv=new ModelAndView();
        Product product=service.findById(id);
        mv.addObject("product",product);
        mv.setViewName("product-show");
        return mv;
    }

    //数据回显
    @RequestMapping("/updateById")
    public ModelAndView updateById(@RequestParam(name = "id") String id) throws Exception {
        ModelAndView mv=new ModelAndView();
        Product product=service.findById(id);
        mv.addObject("product",product);
        mv.setViewName("product-update");
        return mv;
    }

    @RequestMapping("/updateProduct")
    public String updateProduct(Product product){
        System.out.println("product===="+product);
        service.updateProduct(product);
        return "redirect:findAll.do";
    }

    @RequestMapping("/deleteByIds")
    public String deleteByIds(String[] ids){
        if(ids!=null){
            service.deleteByIds(ids);
        }
        return "redirect:findAll.do";
    }
}

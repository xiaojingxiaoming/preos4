package com.kgc.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.domain.SysLog;
import com.kgc.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysLogController {
    @Autowired
    private ISysLogService logService;
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,
                                @RequestParam(name = "size",required = true,defaultValue = "4")Integer size,
                                @RequestParam(name = "search",required =false,defaultValue = "")String search) throws Exception {
        ModelAndView mv=new ModelAndView();
       List<SysLog> logList= logService.findAll(page,size,search);
        System.out.println(page+"-----------"+size);
        PageInfo pageInfo=new PageInfo(logList);
       mv.addObject("pageInfo",pageInfo);
       mv.addObject("search",search);
       mv.setViewName("syslog-list");
       return mv;
    }
    @RequestMapping("/deleteById")
    public String deleteById(@RequestParam(name = "ids")String[] ids){
        if(ids!=null){
            logService.deleteById(ids);
        }
        return "redirect:findAll.do";
    }
}

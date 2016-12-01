package com.yuhi.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.yuhi.sys.facade.SysUserFacade;
@Controller
public class SysIndexController {
	
	@Resource
	private SysUserFacade sysUserFacade;


    @RequestMapping("/sysindex.html")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView ret = new ModelAndView();
        JSONObject user = this.sysUserFacade.getById("admin");
        System.out.println(user);
        
        return ret;
    }
    

    
    

}

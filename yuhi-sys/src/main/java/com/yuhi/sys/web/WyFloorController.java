package com.yuhi.sys.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.yuhi.sys.facade.WyFloorFacade;
/**
* User: lsl
* Date: 13-8-26
* Time: 上午1:57
* 控制器层
*/
@Controller
@RequestMapping("/wyfloor")
public class WyFloorController {
	@Autowired
	private WyFloorFacade wyFloorFacade;
	
	@RequestMapping("/index.html")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView ret = new ModelAndView();
        JSONObject obj = this.wyFloorFacade.getObjDetail("admin");
        System.out.println(obj);
        return ret;
    }

}
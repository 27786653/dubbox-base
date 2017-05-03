package com.yuhi.sys.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.yuhi.sys.entity.MapData;
import com.yuhi.sys.entity.dtData;
import com.yuhi.sys.facade.WyRoomFacade;
/**
* User: lsl
* Date: 13-8-26
* Time: 上午1:57
* 控制器层
*/
@Controller
@RequestMapping("/wyroom")
public class WyRoomController {
	@Autowired
	private WyRoomFacade wyRoomFacade;
	
	@RequestMapping(params="index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView ret = new ModelAndView("modules/room/room-list");
        JSONObject obj = this.wyRoomFacade.getObjDetail("admin");
        ret.addObject("name", "你哈皮");
        System.out.println(obj);
        return ret;
    }
	
	@RequestMapping(params="update")
    public ModelAndView update(HttpServletRequest request,HttpServletResponse response,String id) throws Exception {
		System.out.println(id);
		ModelAndView ret = new ModelAndView("modules/room/room-update");
        return ret;
    }
	@RequestMapping(params="add")
    public ModelAndView add(HttpServletRequest request,HttpServletResponse response) throws Exception {
		MapData md=new MapData(request, "");
		ModelAndView ret = new ModelAndView("modules/room/room-update");
        return ret;
    }
	@RequestMapping(params="delete")
    public ModelAndView delete(HttpServletRequest request,HttpServletResponse response,String id) throws Exception {
		System.out.println(id);
		ModelAndView ret = new ModelAndView("modules/room/room-update");
        return ret;
    }
	
	@RequestMapping(value="/datagird.json",produces = "application/json; charset=utf-8")
	@ResponseBody
    public dtData datagird(HttpServletRequest request,
    		HttpServletResponse response, Integer curPage
    		,Integer pageSize,String sortName,String sortOrder) throws Exception {
        MapData md=new MapData(request);
        dtData obj = this.wyRoomFacade.datagrid(md, curPage, pageSize);
		return obj;
    }
	@RequestMapping(value="/allroom.json",produces = "application/json; charset=utf-8")
	@ResponseBody
    public List<JSONObject> allroomForTree(HttpServletRequest request,
    		HttpServletResponse response) throws Exception {
        MapData md=new MapData(request);
        List<JSONObject> obj= wyRoomFacade.getTreeData(md);
		return obj;
    }
}
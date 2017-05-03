package com.yuhi.base.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuhi.base.entity.SysFile;
import com.yuhi.base.service.SysFileComService;
import com.yuhi.constant.Const;
import com.yuhi.support.RequestUtils;
import com.yuhi.support.ResponseUtils;

@Controller
public class CommController extends BaseController {
	
	
	@Autowired
	private SysFileComService sysFileComService;
	
    
    @RequestMapping("/comm/file/list.json")
    public void list(HttpServletRequest request, HttpServletResponse response, String keys) throws Exception {

    	if(StringUtils.isBlank(keys)){
            ResponseUtils.putJsonFailureResponse(response, "未指定必要参数：键值");
            return;
    	}
    	
    	String[] keyarr = keys.split(",");
        JSONArray jsonArray = new JSONArray();
        List<SysFile> dataList = this.sysFileComService.getInfoList(keyarr);
        if (dataList != null && dataList.size() > 0) {
            JSONObject json = null;
            SysFile data = null;
            for (int i = 0; i < dataList.size(); i++) {
                data = dataList.get(i);
                json = new JSONObject();
                json.put("key", data.getKey());
                json.put("type", data.getType());
                json.put("name", data.getName());
                json.put("bytes", data.getBytes());
                json.put("dataPath", data.getDataPath());
                json.put("dataGroup", data.getDataGroup());
                json.put("expired", data.getExpired());
                jsonArray.add(i, json);
            }
        }
        ResponseUtils.putJsonResponse(response, jsonArray);
    }
    
    /**
     * <B>方法名称：</B>推送文件<BR>
     * <B>概要说明：</B><BR>
     * 
     * @param request 页面请求
     * @param response 页面响应
     * @param type 分类
     * @param expired 是否过期
     * @param field 域名
     * @param descInfo 备注信息
     * @throws Exception 预想外异常
     */
    @RequestMapping("/comm/file/put.json")
    public void put(HttpServletRequest request, HttpServletResponse response, String type, Boolean expired, String field, String descInfo) throws Exception {
        if (StringUtils.isBlank(type)) {
            ResponseUtils.putJsonFailureResponse(response, "未指定必要参数：分类");
            return;
        }
        if (StringUtils.isBlank(field)) {
            field = "file";
        }
        String key = this.sysFileComService.key();
        String userId = RequestUtils.getCurrentUserId(request) == null ? Const.SYS_INIT : RequestUtils.getCurrentUserId(request);
        Date expireTime = new Date();
        MultipartFile mf = RequestUtils.getUploadFile(request);
    	if (mf == null || mf.isEmpty()) {
            ResponseUtils.putJsonFailureResponse(response, "未指定上传文件");
            return;
        }
        JSONObject ret = this.sysFileComService.put(key, type, userId, mf, expireTime, descInfo);
        ResponseUtils.putJsonResponse(response, ret);
    }

    /**
     * <B>方法名称：</B>删除指定文件<BR>
     * <B>概要说明：</B><BR>
     * 
     * @param request 页面请求
     * @param response 页面响应
     * @param keys 键值
     * @throws Exception 预想外异常
     */
    @RequestMapping("/comm/file/expire.json")
    public void expire(HttpServletRequest request, HttpServletResponse response, String[] keys) throws Exception {
        this.sysFileComService.expire(keys);
        ResponseUtils.putJsonSuccessResponse(response);
    }    
    



    
    
    
}

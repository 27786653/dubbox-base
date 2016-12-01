package com.yuhi.base.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.yuhi.base.dao.SysDicComDao;


@Service
public class SysDicComService {

	@Autowired
	private SysDicComDao sysDicComDao;
	
	/**
	 * 根据业务代码获取字典集合
	 * @param type
	 * @return
	 */
	public List<JSONObject> getTypeList(String type){
		return sysDicComDao.getTypeList(type);
	}
	/**
	 * 获取单个字典对象
	 * @param id
	 * @return
	 */
	public JSONObject getCodeById(String id){
		return sysDicComDao.getCodeById(id);
	}
	/**
	 * 添加字典记录
	 * @param id
	 * @return
	 */
	public boolean saveObj(JSONObject json){
		return sysDicComDao.insert(json)!=-1;
	}
	/**
	 * 删除字段记录
	 * @param id
	 * @return
	 */
	public boolean delbyId(String id){
		return sysDicComDao.delete(id)!=-1;
	}
	/**
	 * 删除字段记录
	 * @param id
	 * @param paramMap 
	 * @return
	 */
	public boolean updateDic(String id, Map<String, Object> paramMap){
		return sysDicComDao.update(paramMap, id)!=-1;
	}
	
}

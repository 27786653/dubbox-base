package com.yuhi.sys.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;
import com.yuhi.base.JdbcTemplatesDao;

@Repository("sysUserDao")
public class SysUserDao extends JdbcTemplatesDao {
	
	private static final String SQL_TABLE_NAME = "SYS_USER";
	private static final String SQL_SELECT_SYS_USER = "SELECT * FROM SYS_USER";
	private static final String SQL_SELECT_SYS_USER_BYID = "SELECT * FROM SYS_USER WHERE ID = ?";
	
	public List<JSONObject> getList() throws Exception {
		return super.queryForJsonList(SQL_SELECT_SYS_USER);
	}
	
	public JSONObject getById(String id){
		return super.queryForJsonObject(SQL_SELECT_SYS_USER_BYID, id);
	}
	
	public int insert(JSONObject jsonObject) {
		return super.insert(jsonObject);
	}
	public int update(JSONObject jsonObject) throws Exception {
		return super.insert(jsonObject);
	}

	@Override
	protected String setControllerTable() {
		// TODO Auto-generated method stub
		return SQL_TABLE_NAME;
	}
}

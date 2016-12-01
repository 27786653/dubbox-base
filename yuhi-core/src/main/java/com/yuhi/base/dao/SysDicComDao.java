package com.yuhi.base.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;
import com.yuhi.base.JdbcTemplatesDao;


@Repository
public class SysDicComDao extends JdbcTemplatesDao{

	/** SQL文本：获取业务代码数据 */
	private static final String SQL_SELECT_CODE_LIST = "SELECT ID,DIC_CODE,DIC_INFO FROM `SYS_DIC_INFO` WHERE DIC_CODE=?";
	/** SQL文本 */
	private static final String SQL_SELECT_CODE_BY_ID = "SELECT ID,TYPE_CODE,DIC_CODE,DIC_INFO,CREATE_BY,CREATE_TIME,UPDATE_BY,UPDATE_TIME,SOFT,DEL_FLAG FROM `SYS_DIC_INFO` WHERE ID=? ";
	
	public List<JSONObject> getTypeList(String type){
		return this.queryForJsonList(SQL_SELECT_CODE_LIST, type);
	}
	
	public JSONObject getCodeById(String id){
		return this.queryForJsonObject(SQL_SELECT_CODE_BY_ID, id);
	}

	@Override
	protected String setControllerTable() {
		// TODO Auto-generated method stub
		return "sys_dic_info";
	}
	
}

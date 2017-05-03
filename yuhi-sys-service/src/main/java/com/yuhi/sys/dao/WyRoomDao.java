package com.yuhi.sys.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.alibaba.fastjson.JSONObject;
import com.yuhi.base.JdbcTemplatesDao;
/**
* User: lsl
* Date: 13-8-26
* Time: 上午1:57
* wyRoom表数据操作
*/
@Repository("wyRoomDao")
public class WyRoomDao extends JdbcTemplatesDao {

	private static final String SQL_SELECT = "SELECT ID,R_CODE,R_STATE,R_ACEAGE,R_PUBLIC_ACEAGE,R_TYPE,R_APARTMENT,R_PURPOSE,R_RENOVATION_FLAG,R_ADDRESS,R_DIRECT,R_DESCS,R_FLAG,F_ID FROM WY_ROOM";
	private static final String SQL_SELECT_BYID = "SELECT ID,R_CODE,R_STATE,R_ACEAGE,R_PUBLIC_ACEAGE,R_TYPE,R_APARTMENT,R_PURPOSE,R_RENOVATION_FLAG,R_ADDRESS,R_DIRECT,R_DESCS,R_FLAG,F_ID FROM WY_ROOM WHERE ID = ?";
	
	public List<JSONObject> getList(String sql,Object[] param) throws Exception {
		return super.queryForJsonList(SQL_SELECT+" where 1=1"+sql,param);
	}
	
	public JSONObject getById(String id){
		return super.queryForJsonObject(SQL_SELECT_BYID, id);
	}

	@Override
	protected String setControllerTable() {
		// TODO Auto-generated method stub
		return "WY_ROOM";
	}

	
}
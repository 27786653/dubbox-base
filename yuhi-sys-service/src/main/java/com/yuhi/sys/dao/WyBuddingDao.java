package com.yuhi.sys.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.alibaba.fastjson.JSONObject;
import com.yuhi.base.JdbcTemplatesDao;
/**
* User: lsl
* Date: 13-8-26
* Time: 上午1:57
* wyBudding表数据操作
*/
@Repository("wyBuddingDao")
public class WyBuddingDao extends JdbcTemplatesDao {

	private static final String SQL_SELECT = "SELECT ID,B_ADDRESS,B_TYPE,B_NAME,B_STRUC,B_DIRECT,B_DESCS,B_CODE,M_ID,B_FLAG FROM WY_BUDDING";
	private static final String SQL_SELECT_BYID = "SELECT ID,B_ADDRESS,B_TYPE,B_NAME,B_STRUC,B_DIRECT,B_DESCS,B_CODE,M_ID,B_FLAG FROM WY_BUDDING WHERE ID = ?";
	
	public List<JSONObject> getList(String sql,Object[] param) throws Exception {
		return super.queryForJsonList(SQL_SELECT+" where 1=1"+sql,param);
	}
	
	public JSONObject getById(String id){
		return super.queryForJsonObject(SQL_SELECT_BYID, id);
	}

	@Override
	protected String setControllerTable() {
		// TODO Auto-generated method stub
		return "WY_BUDDING";
	}

	
}
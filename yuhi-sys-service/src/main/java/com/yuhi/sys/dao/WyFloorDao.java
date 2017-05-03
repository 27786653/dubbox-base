package com.yuhi.sys.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.alibaba.fastjson.JSONObject;
import com.yuhi.base.JdbcTemplatesDao;
/**
* User: lsl
* Date: 13-8-26
* Time: 上午1:57
* wyFloor表数据操作
*/
@Repository("wyFloorDao")
public class WyFloorDao extends JdbcTemplatesDao {

	private static final String SQL_SELECT = "SELECT ID,B_ID,F_ROOM_COUNT,F_CODE,F_DESCS,F_FLAG FROM WY_FLOOR";
	private static final String SQL_SELECT_BYID = "SELECT ID,B_ID,F_ROOM_COUNT,F_CODE,F_DESCS,F_FLAG FROM WY_FLOOR WHERE ID = ?";
	
	public List<JSONObject> getList(String sql,Object[] param) throws Exception {
		return super.queryForJsonList(SQL_SELECT+" where 1=1"+sql,param);
	}
	
	public JSONObject getById(String id){
		return super.queryForJsonObject(SQL_SELECT_BYID, id);
	}

	@Override
	protected String setControllerTable() {
		// TODO Auto-generated method stub
		return "WY_FLOOR";
	}

	
}
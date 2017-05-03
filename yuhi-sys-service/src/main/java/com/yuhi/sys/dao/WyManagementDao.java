package com.yuhi.sys.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.alibaba.fastjson.JSONObject;
import com.yuhi.base.JdbcTemplatesDao;
/**
* User: lsl
* Date: 13-8-26
* Time: 上午1:57
* wyManagement表数据操作
*/
@Repository("wyManagementDao")
public class WyManagementDao extends JdbcTemplatesDao {

	private static final String SQL_SELECT = "SELECT ID,M_NAME,M_NO,M_ADDRESS,M_RESPONSIBLE_NAME,M_RESPONSIBLE_PHONE,M_CALL,M_BUDDING_ACREAGE,M_TOTAL_ACREAGE,M_TOTAL_PUBLIC_ACREAGE,M_CAR_ACREAGE,M_ROAD_ACEAGE,M_GREEN_ACEAGE,M_CAR_NUM,M_BUDDING_NUM,M_DESCS,M_FLAG FROM WY_MANAGEMENT";
	private static final String SQL_SELECT_BYID = "SELECT ID,M_NAME,M_NO,M_ADDRESS,M_RESPONSIBLE_NAME,M_RESPONSIBLE_PHONE,M_CALL,M_BUDDING_ACREAGE,M_TOTAL_ACREAGE,M_TOTAL_PUBLIC_ACREAGE,M_CAR_ACREAGE,M_ROAD_ACEAGE,M_GREEN_ACEAGE,M_CAR_NUM,M_BUDDING_NUM,M_DESCS,M_FLAG FROM WY_MANAGEMENT WHERE ID = ?";
	
	public List<JSONObject> getList(String sql,Object[] param) throws Exception {
		return super.queryForJsonList(SQL_SELECT+" where 1=1"+sql,param);
	}
	
	public JSONObject getById(String id){
		return super.queryForJsonObject(SQL_SELECT_BYID, id);
	}

	@Override
	protected String setControllerTable() {
		// TODO Auto-generated method stub
		return "WY_MANAGEMENT";
	}

	
}
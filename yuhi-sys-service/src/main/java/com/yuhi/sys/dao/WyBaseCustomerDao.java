package com.yuhi.sys.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.alibaba.fastjson.JSONObject;
import com.yuhi.base.JdbcTemplatesDao;
/**
* User: lsl
* Date: 13-8-26
* Time: 上午1:57
* wyBaseCustomer表数据操作
*/
@Repository("wyBaseCustomerDao")
public class WyBaseCustomerDao extends JdbcTemplatesDao {

	private static final String SQL_SELECT = "SELECT ID,BC_NAME,BC_ATTRIBUTE,BC_CONCAT_NAME,BC_CONCAT_PHONE,BC_CALLNUM,BC_TYPE,BC_BIRTHER_DATE,BC_CREATE_BY,BC_CREATE_DATE,BC_UPDATE_BY,BC_UPDATE_DATE,BC_IDCARD,BC_EMAIL,BC_QQ_NUM,BC_WORK,BC_URGENT_PHONE FROM WY_BASE_CUSTOMER";
	private static final String SQL_SELECT_BYID = "SELECT ID,BC_NAME,BC_ATTRIBUTE,BC_CONCAT_NAME,BC_CONCAT_PHONE,BC_CALLNUM,BC_TYPE,BC_BIRTHER_DATE,BC_CREATE_BY,BC_CREATE_DATE,BC_UPDATE_BY,BC_UPDATE_DATE,BC_IDCARD,BC_EMAIL,BC_QQ_NUM,BC_WORK,BC_URGENT_PHONE FROM WY_BASE_CUSTOMER WHERE ID = ?";
	
	public List<JSONObject> getList(String sql,Object[] param) throws Exception {
		return super.queryForJsonList(SQL_SELECT+" where 1=1"+sql,param);
	}
	
	public JSONObject getById(String id){
		return super.queryForJsonObject(SQL_SELECT_BYID, id);
	}

	@Override
	protected String setControllerTable() {
		// TODO Auto-generated method stub
		return "WY_BASE_CUSTOMER";
	}

	
}
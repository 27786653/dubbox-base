package com.yuhi.sys.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

import com.yuhi.sys.entity.MapData;
import com.yuhi.sys.entity.dtData;
import com.yuhi.sys.dao.WyBaseCustomerDao;
import com.yuhi.sys.facade.WyBaseCustomerFacade;
/**
* User: lsl
* Date: 13-8-26
* Time: 上午1:57
* WyBaseCustomer服务实现
*	
*/
@Service("wyBaseCustomerService")
@com.alibaba.dubbo.config.annotation.Service(interfaceClass=com.yuhi.sys.facade.WyBaseCustomerFacade.class, protocol = {"rest", "dubbo"})
public class WyBaseCustomerService implements WyBaseCustomerFacade{
	/**
	 * 导入数据操作层
	 */
	@Autowired
	private WyBaseCustomerDao wyBaseCustomerDao;

	@Override
	public boolean save(JSONObject entity)  throws Exception{
		return wyBaseCustomerDao.insert(entity)>-1;
	}

	@Override
	public boolean Update(Map<String, Object> paramMap, String id)  throws Exception{
		return wyBaseCustomerDao.update(paramMap, id)>-1;
	}

	@Override
	public JSONObject getObjDetail(String id) throws Exception {
		return wyBaseCustomerDao.getById(id);
	}

	@Override
	public dtData datagrid(MapData md,Integer pageNum,Integer pagesize) throws Exception{
		StringBuffer sql=new StringBuffer(" FROM WY_BASE_CUSTOMER");
		List<String> args=new ArrayList<String>(md.size());
//		条件构造
		
		//		查询
		Long total=wyBaseCustomerDao.queryForCountNum("select count(1) "+sql.toString(), args.toArray());
		String selectSql="SELECT ID,BC_NAME,BC_ATTRIBUTE,BC_CONCAT_NAME,BC_CONCAT_PHONE,BC_CALLNUM,BC_TYPE,BC_BIRTHER_DATE,BC_CREATE_BY,BC_CREATE_DATE,BC_UPDATE_BY,BC_UPDATE_DATE,BC_IDCARD,BC_EMAIL,BC_QQ_NUM,BC_WORK,BC_URGENT_PHONE ";
		List<JSONObject> objlist=wyBaseCustomerDao.queryForJsonListPage(selectSql+sql.toString(), pageNum, pagesize, args.toArray());
		
		dtData dt=new dtData();
		dt.setData(objlist);
		dt.setCurPage(pageNum);
		dt.setTotalRows(total);
		return dt; 
	}
	
}
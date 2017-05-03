package com.yuhi.sys.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

import com.yuhi.sys.entity.MapData;
import com.yuhi.sys.entity.dtData;
import com.yuhi.sys.dao.WyManagementDao;
import com.yuhi.sys.facade.WyManagementFacade;
/**
* User: lsl
* Date: 13-8-26
* Time: 上午1:57
* WyManagement服务实现
*	
*/
@Service("wyManagementService")
@com.alibaba.dubbo.config.annotation.Service(interfaceClass=com.yuhi.sys.facade.WyManagementFacade.class, protocol = {"rest", "dubbo"})
public class WyManagementService implements WyManagementFacade{
	/**
	 * 导入数据操作层
	 */
	@Autowired
	private WyManagementDao wyManagementDao;

	@Override
	public boolean save(JSONObject entity)  throws Exception{
		return wyManagementDao.insert(entity)>-1;
	}

	@Override
	public boolean Update(Map<String, Object> paramMap, String id)  throws Exception{
		return wyManagementDao.update(paramMap, id)>-1;
	}

	@Override
	public JSONObject getObjDetail(String id) throws Exception {
		return wyManagementDao.getById(id);
	}

	@Override
	public dtData datagrid(MapData md,Integer pageNum,Integer pagesize) throws Exception{
		StringBuffer sql=new StringBuffer(" FROM WY_MANAGEMENT");
		List<String> args=new ArrayList<String>(md.size());
//		条件构造
		
		//		查询
		Long total=wyManagementDao.queryForCountNum("select count(1) "+sql.toString(), args.toArray());
		String selectSql="SELECT ID,M_NAME,M_ADDRESS,M_RESPONSIBLE_NAME,M_RESPONSIBLE_PHONE,M_CALL,M_BUDDING_ACREAGE,M_TOTAL_ACREAGE,M_TOTAL_PUBLIC_ACREAGE,M_CAR_ACREAGE,M_ROAD_ACEAGE,M_GREEN_ACEAGE,M_CAR_NUM,M_BUDDING_NUM,M_DESCS,M_FLAG ";
		List<JSONObject> objlist=wyManagementDao.queryForJsonListPage(selectSql+sql.toString(), pageNum, pagesize, args.toArray());
		dtData dt=new dtData();
		dt.setData(objlist);
		dt.setCurPage(pageNum);
		dt.setTotalRows(total);
		return dt;  
	}
	
}
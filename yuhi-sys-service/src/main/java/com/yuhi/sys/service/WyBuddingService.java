package com.yuhi.sys.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

import com.yuhi.sys.entity.MapData;
import com.yuhi.sys.entity.dtData;
import com.yuhi.sys.dao.WyBuddingDao;
import com.yuhi.sys.facade.WyBuddingFacade;
/**
* User: lsl
* Date: 13-8-26
* Time: 上午1:57
* WyBudding服务实现
*	
*/
@Service("wyBuddingService")
@com.alibaba.dubbo.config.annotation.Service(interfaceClass=com.yuhi.sys.facade.WyBuddingFacade.class, protocol = {"rest", "dubbo"})
public class WyBuddingService implements WyBuddingFacade{
	/**
	 * 导入数据操作层
	 */
	@Autowired
	private WyBuddingDao wyBuddingDao;

	@Override
	public boolean save(JSONObject entity)  throws Exception{
		return wyBuddingDao.insert(entity)>-1;
	}

	@Override
	public boolean Update(Map<String, Object> paramMap, String id)  throws Exception{
		return wyBuddingDao.update(paramMap, id)>-1;
	}

	@Override
	public JSONObject getObjDetail(String id) throws Exception {
		return wyBuddingDao.getById(id);
	}

	@Override
	public dtData datagrid(MapData md,Integer pageNum,Integer pagesize) throws Exception{
		StringBuffer sql=new StringBuffer(" FROM WY_BUDDING wb,wy_management wym where wym.id=wb.m_id ");
		List<String> args=new ArrayList<String>(md.size());
//		条件构造
		
		//		查询
		Long total=wyBuddingDao.queryForCountNum("select count(1) "+sql.toString(), args.toArray());
		String selectSql="SELECT wb.ID,wb.B_ADDRESS,wb.B_TYPE,wb.B_NAME,wb.B_STRUC,wb.B_DIRECT,wb.B_DESCS,wb.B_CODE,wym.m_name,wb.B_FLAG ";
		List<JSONObject> objlist=wyBuddingDao.queryForJsonListPage(selectSql+sql.toString(), pageNum, pagesize, args.toArray());
		dtData dt=new dtData();
		dt.setData(objlist);
		dt.setCurPage(pageNum);
		dt.setTotalRows(total);
		return dt; 
	}
	
}
package com.yuhi.sys.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

import com.yuhi.sys.entity.MapData;
import com.yuhi.sys.entity.dtData;
import com.yuhi.sys.dao.WyFloorDao;
import com.yuhi.sys.facade.WyFloorFacade;
/**
* User: lsl
* Date: 13-8-26
* Time: 上午1:57
* WyFloor服务实现
*	
*/
@Service("wyFloorService")
@com.alibaba.dubbo.config.annotation.Service(interfaceClass=com.yuhi.sys.facade.WyFloorFacade.class, protocol = {"rest", "dubbo"})
public class WyFloorService implements WyFloorFacade{
	/**
	 * 导入数据操作层
	 */
	@Autowired
	private WyFloorDao wyFloorDao;

	@Override
	public boolean save(JSONObject entity)  throws Exception{
		return wyFloorDao.insert(entity)>-1;
	}

	@Override
	public boolean Update(Map<String, Object> paramMap, String id)  throws Exception{
		return wyFloorDao.update(paramMap, id)>-1;
	}

	@Override
	public JSONObject getObjDetail(String id) throws Exception {
		return wyFloorDao.getById(id);
	}

	@Override
	public dtData datagrid(MapData md,Integer pageNum,Integer pagesize) throws Exception{
		StringBuffer sql=new StringBuffer(" FROM WY_FLOOR");
		List<String> args=new ArrayList<String>(md.size());
//		条件构造
		
		//		查询
		Long total=wyFloorDao.queryForCountNum("select count(1) "+sql.toString(), args.toArray());
		String selectSql="SELECT ID,B_ID,F_ROOM_COUNT,F_CODE,F_DESCS,F_FLAG ";
		List<JSONObject> objlist=wyFloorDao.queryForJsonListPage(selectSql+sql.toString(), pageNum, pagesize, args.toArray());
		dtData dt=new dtData();
		dt.setData(objlist);
		dt.setCurPage(pageNum);
		dt.setTotalRows(total);
		return dt; 
	}
	
}
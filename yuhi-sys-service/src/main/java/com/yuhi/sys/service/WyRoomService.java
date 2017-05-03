package com.yuhi.sys.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

import com.yuhi.sys.entity.MapData;
import com.yuhi.sys.entity.dtData;
import com.yuhi.sys.dao.WyRoomDao;
import com.yuhi.sys.facade.WyRoomFacade;
/**
* User: lsl
* Date: 13-8-26
* Time: 上午1:57
* WyRoom服务实现
*	
*/
@Service("wyRoomService")
@com.alibaba.dubbo.config.annotation.Service(interfaceClass=com.yuhi.sys.facade.WyRoomFacade.class, protocol = {"rest", "dubbo"})
public class WyRoomService implements WyRoomFacade{
	/**
	 * 导入数据操作层
	 */
	@Autowired
	private WyRoomDao wyRoomDao;

	@Override
	public boolean save(JSONObject entity)  throws Exception{
		return wyRoomDao.insert(entity)>-1;
	}

	@Override
	public boolean Update(Map<String, Object> paramMap, String id)  throws Exception{
		return wyRoomDao.update(paramMap, id)>-1;
	}

	@Override
	public JSONObject getObjDetail(String id) throws Exception {
		return wyRoomDao.getById(id);
	}

	@Override
	public dtData datagrid(MapData md,Integer pageNum,Integer pagesize) throws Exception{
		StringBuffer sql=new StringBuffer(" FROM WY_ROOM wr LEFT JOIN wy_budding wb on wr.b_id=wb.id LEFT JOIN wy_management wm on wb.m_id=wm.id where 1=1 ");
		List<String> args=new ArrayList<String>(md.size());
//		条件构造
		if(md.containsKey("managerId")){
			sql.append(" and  wr.m_id='"+md.getString("managerId")+"'");
		}
		if(md.containsKey("buddingId")){
			sql.append(" and  wb.id='"+md.getString("buddingId")+"'");
		}
//		查询
		Long total=wyRoomDao.queryForCountNum("select count(1) "+sql.toString(), args.toArray());
		String selectSql="SELECT wr.ID,wr.R_CODE,wr.R_STATE,wr.R_ACEAGE,wr.R_PUBLIC_ACEAGE,wr.R_TYPE,wr.R_APARTMENT,wr.R_PURPOSE,wr.R_RENOVATION_FLAG,wr.R_ADDRESS,R_DIRECT,wr.R_DESCS,wr.R_FLAG,wb.b_name,wm.m_name   ";
		List<JSONObject> objlist=wyRoomDao.queryForJsonListPage(selectSql+sql.toString(), pageNum, pagesize, args.toArray());
		dtData dt=new dtData();
		dt.setData(objlist);
		dt.setCurPage(pageNum);
		dt.setTotalRows(total);
		return dt; 
	}
	@Override
	public List<JSONObject> getTreeData(MapData md) throws Exception{
		StringBuffer sql=new StringBuffer("select `name`,id,pid,`open` from ("+
				"SELECT m_name `name`,id,'0' pId,'true' `open`,id mid  FROM `wy_management` UNION ALL "+
				"SELECT b_name `name`,id,m_id pId,'false' `open`,m_id mid  FROM `wy_budding` UNION ALL "+
				"SELECT f_descs `name`,id,b_id pId,'false' `open`,m_id mid  FROM `wy_floor` UNION ALL "+
				"SELECT r_code `name`,id,f_id pId,'false' `open`,m_id mid  FROM `wy_room` "+
		") roomset ");
		////		条件构造
		List<String> args=new ArrayList<String>(md.size());
		if(md.containsKey("managerId")){
			sql.append(" where  mid='"+md.getString("managerId")+"'");
		}
//		查询
		List<JSONObject> objlist=wyRoomDao.queryForJsonList(sql.toString());
		return objlist; 
	}
}
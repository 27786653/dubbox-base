package com.yuhi.tests.t;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.yuhi.sys.facade.WyBaseCustomerFacade;
import com.yuhi.sys.facade.WyBuddingFacade;
import com.yuhi.sys.facade.WyFloorFacade;
import com.yuhi.sys.facade.WyManagementFacade;
import com.yuhi.sys.facade.WyRoomFacade;
import com.yuhi.util.IDCreate;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring-context.xml","classpath*:spring-mvc.xml"})
public class SpringTestForContexts{

	/**
	* User: lsl
	* Date: 13-8-26
	* Time: 上午1:57
	* Autowired.
	*/
	@Autowired
	private WyBaseCustomerFacade wyBaseCustomerFacade;

	/**
	* User: lsl
	* Date: 13-8-26
	* Time: 上午1:57
	* Test Code.
	*/
		@Test
		public void testWyBaseCustomer() throws Exception{
			JSONObject entity=new JSONObject();
			entity.put("id", IDCreate.getDateString());
			wyBaseCustomerFacade.save(entity);
			String id=IDCreate.getDateString();
			Map<String, Object> paramMap=new HashMap<String, Object>();
			paramMap.put("id", id);
			wyBaseCustomerFacade.Update(paramMap, entity.getString("id"));
			entity=wyBaseCustomerFacade.getObjDetail(id);
			if(!entity.getString("id").equals(id))throw new Exception();
			System.out.println("TestMes id:"+id);
		}
		
		
		/**
		* User: lsl
		* Date: 13-8-26
		* Time: 上午1:57
		* Autowired.
		*/
		@Autowired
		private WyBuddingFacade wyBuddingFacade;

		/**
		* User: lsl
		* Date: 13-8-26
		* Time: 上午1:57
		* Test Code.
		*/
			@Test
			public void testWyBudding() throws Exception{
				JSONObject entity=new JSONObject();
				entity.put("id", IDCreate.getDateString());
				wyBuddingFacade.save(entity);
				String id=IDCreate.getDateString();
				Map<String, Object> paramMap=new HashMap<String, Object>();
				paramMap.put("id", id);
				wyBuddingFacade.Update(paramMap, entity.getString("id"));
				entity=wyBuddingFacade.getObjDetail(id);
				if(!entity.getString("id").equals(id))throw new Exception();
				System.out.println("TestMes id:"+id);
			}
				

/**
* User: lsl
* Date: 13-8-26
* Time: 上午1:57
* Autowired.
*/
@Autowired
private WyManagementFacade wyManagementFacade;

/**
* User: lsl
* Date: 13-8-26
* Time: 上午1:57
* Test Code.
*/
	@Test
	public void testWyManagement() throws Exception{
		JSONObject entity=new JSONObject();
		entity.put("id", IDCreate.getDateString());
		wyManagementFacade.save(entity);
		String id=IDCreate.getDateString();
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("id", id);
		wyManagementFacade.Update(paramMap, entity.getString("id"));
		entity=wyManagementFacade.getObjDetail(id);
		if(!entity.getString("id").equals(id))throw new Exception();
		System.out.println("TestMes id:"+id);
	}
	

/**
* User: lsl
* Date: 13-8-26
* Time: 上午1:57
* Autowired.
*/
@Autowired
private WyFloorFacade wyFloorFacade;

/**
* User: lsl
* Date: 13-8-26
* Time: 上午1:57
* Test Code.
*/
	@Test
	public void testWyFloor() throws Exception{
		JSONObject entity=new JSONObject();
		entity.put("id", IDCreate.getDateString());
		wyFloorFacade.save(entity);
		String id=IDCreate.getDateString();
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("id", id);
		wyFloorFacade.Update(paramMap, entity.getString("id"));
		entity=wyFloorFacade.getObjDetail(id);
		if(!entity.getString("id").equals(id))throw new Exception();
		System.out.println("TestMes id:"+id);
	}
	
	/**
	* User: lsl
	* Date: 13-8-26
	* Time: 上午1:57
	* Autowired.
	*/
	@Autowired
	private WyRoomFacade wyRoomFacade;

	/**
	* User: lsl
	* Date: 13-8-26
	* Time: 上午1:57
	* Test Code.
	*/
		@Test
		public void testWyRoom() throws Exception{
			JSONObject entity=new JSONObject();
			entity.put("id", IDCreate.getDateString());
			wyRoomFacade.save(entity);
			String id=IDCreate.getDateString();
			Map<String, Object> paramMap=new HashMap<String, Object>();
			paramMap.put("id", id);
			wyRoomFacade.Update(paramMap, entity.getString("id"));
			entity=wyRoomFacade.getObjDetail(id);
			if(!entity.getString("id").equals(id))throw new Exception();
			System.out.println("TestMes id:"+id);
		}
		
//	public static void main(String[] args) {
//		System.out.println(ClassLocationUtils.where(RequestMapping.class));
//		
//	}
}

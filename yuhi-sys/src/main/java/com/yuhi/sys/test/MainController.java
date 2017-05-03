package com.yuhi.sys.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuhi.admin.facade.AuthorizationFacade;
import com.yuhi.sys.entity.MapData;
import com.yuhi.sys.entity.dtData;

@Controller
public class MainController {
	
	@RequestMapping("/index.html")
    public String index(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		return "modules/test/test-list"; 
		return "index";
	}
	@RequestMapping("/test.html")
    public String test(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "modules/test/test-list"; 
	}
	/**
	 * 测试数据表格
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/begtable.html")
    public String getdataTable(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "begtable"; 
	}
	
	
	@Autowired
	private AuthorizationFacade user;
	
	@RequestMapping(value="/nav.json",produces = "application/json; charset=utf-8")
	@ResponseBody
    public String navData(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		ClassPathResource res=new ClassPathResource("nav.json");
//		ServletContextResource scr=new ServletContextResource(request.getServletContext(),"resource/datas/navdata.json");
//		String nav= FileUtils.readFileToString(scr.getFile(),"UTF-8");
		String nav="[{\"title\":\"房产管理\",\"icon\":\"fa-cogs\",\"spread\":true,\"children\":[{\"title\":\"项目管理\",\"icon\":\"fa-table\",\"href\":\"begtable.html\"},{\"title\":\"楼宇管理\",\"icon\":\"fa-table\",\"href\":\"begtable.html\"},{\"title\":\"房间管理\",\"icon\":\"fa-table\",\"href\":\"wyroom.html?index\"},{\"title\":\"房间状态统计\",\"icon\":\"fa-table\",\"href\":\"begtable.html\"},{\"title\":\"房间状态图示\",\"icon\":\"fa-table\",\"href\":\"begtable.html\"},{\"title\":\"房屋产权变更\",\"icon\":\"fa-table\",\"href\":\"begtable.html\"}]},{\"title\":\"客户管理\",\"icon\":\"fa-cogs\",\"spread\":false,\"children\":[{\"title\":\"临时客户管理\",\"icon\":\"fa-table\",\"href\":\"wyroom.html?index\"},{\"title\":\"房间客户档案\",\"icon\":\"fa-table\",\"href\":\"begtable.html\"},{\"title\":\"客户迁入管理\",\"icon\":\"fa-table\",\"href\":\"begtable.html\"},{\"title\":\"客户迁出管理\",\"icon\":\"fa-table\",\"href\":\"begtable.html\"},{\"title\":\"客户入住历史\",\"icon\":\"fa-table\",\"href\":\"begtable.html\"},{\"title\":\"客户入住统计\",\"icon\":\"fa-table\",\"href\":\"begtable.html\"},{\"title\":\"客户事件管理\",\"icon\":\"fa-table\",\"href\":\"begtable.html\"},{\"title\":\"家庭成员管理\",\"icon\":\"fa-table\",\"href\":\"begtable.html\"},{\"title\":\"房间客户概况\",\"icon\":\"fa-table\",\"href\":\"begtable.html\"}]},{\"title\":\"基本元素1\",\"icon\":\"fa-cubes\",\"spread\":false,\"children\":[{\"title\":\"按钮\",\"icon\":\"&#xe641;\",\"href\":\"button.html\"},{\"title\":\"表单\",\"icon\":\"&#xe63c;\",\"href\":\"form.html\"},{\"title\":\"表格\",\"icon\":\"&#xe63c;\",\"href\":\"table.html\"},{\"title\":\"导航\",\"icon\":\"&#xe609;\",\"href\":\"nav.html\"},{\"title\":\"Tab选项卡\",\"icon\":\"&#xe62a;\",\"href\":\"tab.html\"},{\"title\":\"辅助性元素\",\"icon\":\"&#xe60c;\",\"href\":\"auxiliar.html\"}]},{\"title\":\"组件\",\"icon\":\"fa-cogs\",\"spread\":false,\"children\":[{\"title\":\"Datatable\",\"icon\":\"fa-table\",\"href\":\"begtable.html\"},{\"title\":\"Navbar组件\",\"icon\":\"fa-navicon\",\"href\":\"navbar.html\"}]},{\"title\":\"第三方组件\",\"icon\":\"&#x1002;\",\"spread\":false,\"children\":[{\"title\":\"iCheck组件\",\"icon\":\"fa-check-square-o\",\"href\":\"icheck.html\"}]},{\"title\":\"地址本\",\"icon\":\"fa-address-book\",\"href\":\"\",\"spread\":false,\"children\":[{\"title\":\"Github\",\"icon\":\"fa-github\",\"href\":\"https://www.github.com/\"},{\"title\":\"QQ\",\"icon\":\"fa-qq\",\"href\":\"http://www.qq.com/\"},{\"title\":\"Fly社区\",\"icon\":\"&#xe609;\",\"href\":\"http://fly.layui.com/\"},{\"title\":\"新浪微博\",\"icon\":\"fa-weibo\",\"href\":\"http://weibo.com/\"}]},{\"title\":\"这是一级导航\",\"icon\":\"fa-stop-circle\",\"href\":\"https://www.baidu.com\",\"spread\":false}]";
		return nav; 
//		return user.getpermissionMenus("4028d881436d514601436d5215ac0043", "base");
	}
	@RequestMapping(value="/aaa.json",produces = "application/json; charset=utf-8")
	@ResponseBody
    public String aaa(dtData dt,HttpServletRequest request, HttpServletResponse response) throws Exception {
//		if(dt.getDraw()==null)return null;
//		
//		List<JSONObject> data = new ArrayList<JSONObject>();
//		for (int i = dt.getStart(); i < dt.getStart()+dt.getLength(); i++) {
//			JSONObject e=new JSONObject();
//			e.put("name", i);
//			e.put("age", i);
//			data.add(e);
//		}
//		
//		dt.setData(data);
//		dt.setRecordsTotal(100);
//		dt.setRecordsFiltered(100);
		//		ClassPathResource res=new ClassPathResource("nav.json");
		
//		{curPage=1, pageSize=10}
		MapData md=new MapData(request);
		String s="{\"success\":true,\"totalRows\":20,\"curPage\":1,\"data\":[{\"ID\":202,\"CHAR\":\"1\",\"TEXT\":\"TEXT_TEXT_TEXT_TEXT_TEXT_TEXT_TEXT_TEXT_TEXT_TEXT_TEXT_1\",\"NUM\":11.2,\"XH\":1},{\"ID\":201,\"CHAR\":\"111\",\"TEXT\":\"TEXT_TEXT_TEXT_TEXT_TEXT_TEXT_TEXT_TEXT_TEXT_TEXT_TEXT_2\",\"XH\":2},{\"ID\":200,\"CHAR\":\"200\",\"TEXT\":\"TEXT_TEXT_TEXT_TEXT_TEXT_TEXT_TEXT_TEXT_TEXT_TEXT_TEXT_3\",\"XH\":3},{\"ID\":199,\"CHAR\":\"199\",\"XH\":4},{\"ID\":32,\"CHAR\":\"34\",\"NUM\":12.1,\"XH\":5},{\"ID\":16,\"CHAR\":\"test\",\"NUM\":16.1,\"XH\":6},{\"ID\":15,\"CHAR\":\"zifu15\",\"DATE\":\"2012-05-15\",\"TIME\":\"15:01:01\",\"NUM\":1.15,\"XH\":7},{\"ID\":14,\"CHAR\":\"zifu14\",\"DATE\":\"2012-05-14\",\"TIME\":\"14:01:01\",\"NUM\":1.14,\"XH\":8},{\"ID\":13,\"CHAR\":\"zifu13\",\"DATE\":\"2012-05-13\",\"TIME\":\"13:01:01\",\"NUM\":1.13,\"XH\":9},{\"ID\":12,\"CHAR\":\"zifu12\",\"DATE\":\"2012-05-12\",\"TIME\":\"12:01:01\",\"NUM\":1.12,\"XH\":10},{\"ID\":2021,\"CHAR\":\"1\",\"TEXT\":\"TEXT_TEXT_TEXT_TEXT_TEXT_TEXT_TEXT_TEXT_TEXT_TEXT_TEXT_1\",\"NUM\":11.2,\"XH\":11},{\"ID\":2011,\"CHAR\":\"111\",\"TEXT\":\"TEXT_TEXT_TEXT_TEXT_TEXT_TEXT_TEXT_TEXT_TEXT_TEXT_TEXT_2\",\"XH\":12},{\"ID\":2000,\"CHAR\":\"200\",\"TEXT\":\"TEXT_TEXT_TEXT_TEXT_TEXT_TEXT_TEXT_TEXT_TEXT_TEXT_TEXT_3\",\"XH\":13},{\"ID\":1991,\"CHAR\":\"199\",\"XH\":14},{\"ID\":321,\"CHAR\":\"34\",\"NUM\":12.1,\"XH\":15},{\"ID\":161,\"CHAR\":\"test\",\"NUM\":16.1,\"XH\":16},{\"ID\":151,\"CHAR\":\"zifu15\",\"DATE\":\"2012-05-15\",\"TIME\":\"15:01:01\",\"NUM\":1.15,\"XH\":17},{\"ID\":141,\"CHAR\":\"zifu14\",\"DATE\":\"2012-05-14\",\"TIME\":\"14:01:01\",\"NUM\":1.14,\"XH\":18},{\"ID\":131,\"CHAR\":\"zifu13\",\"DATE\":\"2012-05-13\",\"TIME\":\"13:01:01\",\"NUM\":1.13,\"XH\":19},{\"ID\":121,\"CHAR\":\"zifu12\",\"DATE\":\"2012-05-12\",\"TIME\":\"12:01:01\",\"NUM\":1.12,\"XH\":20}]}";
		return s; 
	}
}

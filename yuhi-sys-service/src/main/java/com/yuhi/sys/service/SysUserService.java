package com.yuhi.sys.service;

import org.springframework.stereotype.Service;

import com.yuhi.sys.entity.SysUser;
import com.yuhi.sys.facade.SysUserFacade;


@Service("sysUserService")
@com.alibaba.dubbo.config.annotation.Service(interfaceClass=com.yuhi.sys.facade.SysUserFacade.class, protocol = {"rest", "dubbo"})
public class SysUserService implements SysUserFacade {


	public void testget() {
		//http://localhost:8888/yuhi-sys-service/sysUserService/testget
		System.out.println("测试...get");
	}
	
	public SysUser getUser() {
		//http://localhost:8888/yuhi-sys-service/sysUserService/getUser
    	SysUser user = new SysUser();
    	user.setId("1001");
    	user.setName("张三");
    	return user;
	}

	public SysUser getUser(Integer id) {
		//http://localhost:8888/yuhi-sys-service/sysUserService/get/1001
		System.out.println(id);
		System.out.println("测试...get");
		SysUser user = new SysUser();
    	user.setId("1001");
    	user.setName("张三");
    	return user;
	}

	public SysUser getUser(Integer id, String name) {
		//http://localhost:8888/yuhi-sys-service/sysUserService/get/1001/z3
		System.out.println(id);
		System.out.println(name);
		System.out.println("测试...get");
		SysUser user = new SysUser();
    	user.setId("1001");
    	user.setName("张三");
    	return user;
	}
	
	public void testpost() {
    	System.out.println("测试...post");
	}
    
	public SysUser postUser(SysUser user) {
    	System.out.println(user.getName());
    	System.out.println("测试...postUser");
    	SysUser user1 = new SysUser();
    	user.setId("1001");
    	user1.setName("张三");
    	return user1;
	}

	public SysUser postUser(String id) {
		System.out.println(id);
		System.out.println("测试...get");
		SysUser user = new SysUser();
    	user.setId("1001");
    	user.setName("张三");
    	return user;
	}


}

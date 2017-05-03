package com.yuhi.base.security.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/***
 * 单点注销登出过滤器
 * 清除用户授权信息集合
 * @author gaoyuandong
 * @mail   466862016@qq.com
 * @date 2015年11月4日 上午11:11:13
 */
public class CasLogoutFilter extends LogoutFilter {

	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		Subject subject = getSubject(request, response);
		return super.preHandle(request, response);
	}
}

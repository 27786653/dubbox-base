package com.yuhi.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;

import com.yuhi.support.SysEventService;

/**
 * 日志拦截器
 * 
 * @author ShenHuaJie
 * @version 2016年6月14日 下午6:18:46
 */
public class EventInterceptor extends BaseInterceptor {
	@Autowired
	private SysEventService sysEventService;

	private final ThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("ThreadLocal StartTime");

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 开始时间（该数据只有当前请求的线程可见）
		startTimeThreadLocal.set(System.currentTimeMillis());
		return super.preHandle(request, response, handler);
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// 保存日志
		sysEventService.saveEvent(request, response, ex, startTimeThreadLocal.get(), System.currentTimeMillis());
		super.afterCompletion(request, response, handler, ex);
	}
}
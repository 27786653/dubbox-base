package com.yuhi.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

/**
 * ClassName ServerListerner.
 * @coment: 系统监听器
 * @user: 李森林
 * @category @author www.justintoForest@Gamil.com
 * @since: 2017-05-11 上午 09:43
 */
public abstract class ServerListerner implements ServletContextListener {
	protected final Logger logger = Logger.getLogger(ServerListerner.class);

	public void contextDestroyed(ServletContextEvent contextEvent) {
	}

	public void contextInitialized(ServletContextEvent contextEvent) {
		logger.info("=================================");
		logger.info("系统启动完成!!!"+contextEvent.getServletContext().getServletContextName());
		logger.info("=================================");
	}
}
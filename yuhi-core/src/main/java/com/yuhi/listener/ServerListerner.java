package com.yuhi.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;


public class ServerListerner implements ServletContextListener {
	protected final Logger logger = Logger.getLogger(ServerListerner.class);

	public void contextDestroyed(ServletContextEvent contextEvent) {
	}

	public void contextInitialized(ServletContextEvent contextEvent) {
		logger.info("=================================");
		logger.info("系统启动完成!!!"+contextEvent.getServletContext().getServletContextName());
		logger.info("=================================");
	}
}
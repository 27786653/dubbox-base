package com.yuhi.datasource.manual;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DataSourceHoulder extends AbstractRoutingDataSource {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	private AtomicInteger counter = new AtomicInteger();
	private DataSource master;
	private List<DataSource> slaves;
	
	public  DataSourceHoulder() {
		// TODO Auto-generated constructor stub
		ContextHolder.setContextType(ContextHolder.MASTERSTR);
		slaves=new ArrayList<DataSource>();
	}
	@Override
	protected Object determineCurrentLookupKey() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void afterPropertiesSet(){
	}
	
	@Override
	protected DataSource determineTargetDataSource() {
		String dataSouceName=ContextHolder.getContextType();
		int slavesCount = slaves.size();
		if(slavesCount==0||dataSouceName.equals(ContextHolder.MASTERSTR)){
			logger.info("Change DataSourse for Master..");
			return master;
		}
		if(dataSouceName.equals(ContextHolder.SLAVESTR)){
				int count = counter.incrementAndGet();
				if(count>1000000){
					counter.set(0);
				}
				int index = count%slavesCount;
				logger.info("Change DataSourse for Slavestr..");
				return slaves.get(index);
		}
		logger.error("no DataSource Exits");
		return null;
	}

	public DataSource getMaster() {
		return master;
	}

	public void setMaster(DataSource master) {
		this.master = master;
	}

	public List<DataSource> getSlaves() {
		return slaves;
	}

	public void setSlaves(List<DataSource> slaves) {
		this.slaves = slaves;
	}
	
	
	
	
}

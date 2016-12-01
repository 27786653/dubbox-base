package com.yuhi.dataSource.manual;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
/**
 *	手动数据库切换
 *	代码侵略污染性高！
 */
public class DataSourceHoulder extends AbstractRoutingDataSource {
	/**
	 * 主数据源标识
	 */
	public final static String MASTERSTR="MASTER";
	/**
	 * 从数据源标示
	 */
	public final static String SLAVESTR="SLAVE";
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	
	public  static ThreadLocal<String> data= new ThreadLocal<String>();
	
	private AtomicInteger counter = new AtomicInteger();
	
	private DataSource master;
	private List<DataSource> slaves;
	
	public DataSourceHoulder(){
		ChangeDataSource(MASTERSTR);	
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
		String dataSouceName=data.get();
		if(StringUtils.isEmpty(dataSouceName)||data.get().equals(MASTERSTR)){
			logger.info("Change DataSourse for Master！");
			return master;
		}
		if(data.get().equals(SLAVESTR)){
				int count = counter.incrementAndGet();
				if(count>1000000){
					counter.set(0);
				}
				int n = slaves.size();
				int index = count%n;
				logger.info("Change DataSourse for Slavestr！");
				return slaves.get(index);
		}
		logger.error("no DataSource Exits");
		return null;
	}
	public static void ChangeDataSource(String DataSourceCode){
		DataSourceHoulder.data.set(DataSourceCode);
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

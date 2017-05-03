package com.yuhi.datasource.manual.transaction;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;

import com.yuhi.datasource.manual.ContextHolder;

/**
 * DynamicDataSourceTransactionManager
 * 注意，事务未打开不会到达此处
 * @date 2015-6-19
 */
public class DynamicDataSourceTransactionManager extends
		DataSourceTransactionManager {

	private static final long serialVersionUID = 7160082287881717832L;
	public DynamicDataSourceTransactionManager(){
		ContextHolder.setContextType(ContextHolder.SLAVESTR);
	}
	@Override
	protected void doBegin(Object transaction, TransactionDefinition definition) {
		boolean readOnly = definition.isReadOnly();
		ContextHolder.setContextType(readOnly?ContextHolder.SLAVESTR:ContextHolder.MASTERSTR);
		super.doBegin(transaction, definition);
	}
	@Override
	protected void doCommit(org.springframework.transaction.support.DefaultTransactionStatus status) {
		super.doCommit(status);
	}
	/**
	 * 清理本地线程的数据源 6
	 */
	@Override
	protected void doCleanupAfterCompletion(Object transaction) {
		super.doCleanupAfterCompletion(transaction);
		ContextHolder.setContextType(ContextHolder.SLAVESTR);
	}
}

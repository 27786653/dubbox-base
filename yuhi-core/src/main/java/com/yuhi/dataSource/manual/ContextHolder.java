package com.yuhi.datasource.manual;  
public class ContextHolder {  
    //数据源对应key值，与配置文件中key值相同  
	/**
	 * 主数据源标识
	 */
	public final static String MASTERSTR="MASTER";
	/**
	 * 从数据源标示
	 */
	public final static String SLAVESTR="SLAVE";
	
    //存储当前线程的key值  
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();  
      
    public static void setContextType(String customerType)  
    {  
        contextHolder.set(customerType);  
    }  
    public static String getContextType()  
    {  
        return contextHolder.get();  
    }  
    public static void clearContextType()  
    {  
        contextHolder.remove();  
    }  
      
}  
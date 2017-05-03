package com.yuhi.datasource.aspectdata;  
public class CustomerContextHolder {  
    //数据源对应key值，与配置文件中key值相同  
    public static final String DATA_A = "dataSource1";  
    public static final String DATA_B = "dataSource2";  
      
    //存储当前线程的key值  
    private static final ThreadLocal<String> contextHolder = new ThreadLocal();  
      
    public static void setCustomerType(String customerType)  
    {  
        contextHolder.set(customerType);  
    }  
    public static String getCustomerType()  
    {  
        return contextHolder.get();  
    }  
    public static void clearCustomerType()  
    {  
        contextHolder.remove();  
    }  
      
}  
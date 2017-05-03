package com.yuhi.datasource.aspectdata;  
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;  
public class DynamicDataSource extends AbstractRoutingDataSource {  
    @Override  
    protected Object determineCurrentLookupKey() {  
        // TODO Auto-generated method stub  
        return CustomerContextHolder.getCustomerType();  
    }  
}  
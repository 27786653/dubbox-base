package com.yuhi.monitor.support.dubbo.support.filter;

import java.util.Arrays;

import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;

public class NodeTrackFilter implements Filter {

	
	private static org.slf4j.Logger log = LoggerFactory  
            .getLogger(NodeTrackFilter.class);  
  
    @Override  
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {  
        long start = System.currentTimeMillis();  
        Result result = invoker.invoke(invocation);  
        long loadTimeMillis = System.currentTimeMillis() - start;  
        if (invoker.getUrl() != null) {  
            // log.info("[" +invoker.getInterface() +"] [" + invocation.getMethodName() +"] [" + elapsed +"]" );  
            log.info("[{}], [{}], {}, [{}], [{}], [{}]   ", invoker.getInterface(), invocation.getMethodName(),   
                         Arrays.toString(invocation.getArguments()), result.getValue(),  
                       result.getException(), loadTimeMillis);  
        }  
        return result;  
    }  

}

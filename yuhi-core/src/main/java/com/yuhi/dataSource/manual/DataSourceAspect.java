package com.yuhi.datasource.manual;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import com.yuhi.datasource.manual.annotation.ContextDbType;

@Aspect
//@Component
public class DataSourceAspect {
	private final Logger logger = Logger.getLogger(DataSourceAspect.class);

	@Pointcut("execution (* com.yuhi.dao.*.*(..)) && @annotation(contextDbType)")
	public void aspect(ContextDbType contextDbType) {
		logger.info("begin To Change DataSource!");
	}
/**
 * execution (* org.y0ung.example.custom_annotation_with_spring.service.*.*(..)) && @annotation(young)
 * @param point
 */
	@Before("aspect()")
	public void before(JoinPoint point,ContextDbType contextDbType) {
		 try {
			Map<String, String> map = 
					getDataSourceAspectMethodDescription(point);
			if(map.size()==0){
//				String methodname=point.getSignature().getName();
//		    	if(methodname.contains("add")){
		    		ContextHolder.setContextType(ContextHolder.SLAVESTR);
//		    	}else{
//		    		ContextHolder.setContextType(ContextHolder.MASTERSTR);
//		    	}		    	
			}else{
				ContextHolder.setContextType(map.get("ContextType"));
			}
			logger.info("DataSource is change to slave");
		 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("DataSource is change error");
		}
		
	}

//	@After("aspect() && @annotation(contextDbType)")
//	public void after(JoinPoint point,ContextDbType contextDbType) {
//		logger.info("DataSource is change to master");
//	}
	
     @SuppressWarnings("rawtypes")
    public Map<String, String> getDataSourceAspectMethodDescription(JoinPoint joinPoint)  throws Exception {
         Map<String, String> map = new HashMap<String, String>();
         String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
         for (Method method : methods) {
             if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                 if (clazzs.length == arguments.length) {
                     map.put("ContextType", method.getAnnotation(ContextDbType.class).ContextType());
//                   map.put("methods", method.getAnnotation(ContextDbType.class).methods());
                     break;
                }
            }
        }
         return map;
    }
	
}

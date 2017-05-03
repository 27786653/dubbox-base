package com.yuhi.datasource.manual.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.yuhi.datasource.manual.ContextHolder;

@Retention(RetentionPolicy.RUNTIME)  
@Target(ElementType.METHOD)  
@Documented
@Inherited
public @interface ContextDbType {  
    String ContextType() default ContextHolder.MASTERSTR;  
}  
package com.yuhi.base.web.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Map;

//@Component
//@Lazy(false)
public class SpringUtils implements DisposableBean, ApplicationContextAware {
    private static ApplicationContext context;

    private SpringUtils() {
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
    public void destroy() {
        context = null;
    }
    public static ApplicationContext getApplicationContext() {
        return context;
    }

    public static <T> T getBean(String beanName) {
        Assert.hasText(beanName);
        return (T) context.getBean(beanName);
    }

    public static <T> Map<String, T> getBeanByType(Class<T> clazz) {
        Assert.notNull(clazz);
        return context.getBeansOfType(clazz);
    }
}

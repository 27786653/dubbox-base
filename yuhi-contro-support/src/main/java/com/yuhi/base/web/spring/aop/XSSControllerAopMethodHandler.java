//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yuhi.base.web.spring.aop;


import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class XSSControllerAopMethodHandler {
    public XSSControllerAopMethodHandler() {
    }

    private void htmlClear(Map params) {
        if(params!=null&&params.size()>0) {
            Set set = params.entrySet();
            Iterator var3 = set.iterator();

            while(var3.hasNext()) {
                Entry entry = (Entry)var3.next();
                Object val = entry.getValue();
                if(val != null && val instanceof String) {
                    String str = Objects.toString(val);
                    if(StringUtils.isNotBlank(str)) {
                        str = StringEscapeUtils.escapeHtml4(str);
                    }

                    entry.setValue(str);
                }
            }

        }
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void methodCachePointcut() {
    }

    @Around("methodCachePointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        if(ArrayUtils.isNotEmpty(args)) {
            Object[] result = args;
            int var4 = args.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                Object arg = result[var5];
                if(arg instanceof Map) {
                    Map params = (Map)arg;
                    this.htmlClear(params);
                }
            }
        }

        Object var8 = point.proceed();
        return var8;
    }
}

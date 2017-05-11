package com.yuhi.base.web.message;

import org.springframework.web.servlet.LocaleResolver;

import com.yuhi.base.web.WebUtil;
import com.yuhi.base.web.spring.SpringUtils;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class I18Util {
    public I18Util() {
    }

    public static String getMessage(HttpServletRequest request,String code) {
        RequestContext requestContext = new RequestContext(request);
//		requestContext.changeLocale((Locale) request.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME));
        String message = "";
        try {
            message = requestContext.getMessage(code);
        }catch(Exception e){
            e.printStackTrace();
        }
        return message;
    }
}

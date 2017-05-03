package com.yuhi.base.web.message;

import org.springframework.web.servlet.LocaleResolver;

import com.yuhi.base.web.WebUtil;
import com.yuhi.base.web.spring.SpringUtils;

import java.util.Locale;

public class I18Util {
    public I18Util() {
    }

    public static String getMessage(String code, Object... args) {
        LocaleResolver resolver = (LocaleResolver) SpringUtils.getBean("localeResolver");
        Locale localLocale = resolver.resolveLocale(WebUtil.getRequest());
        return SpringUtils.getApplicationContext().getMessage(code, args, localLocale);
    }
}

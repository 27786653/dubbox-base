package com.yuhi.util;

import com.yuhi.Constants;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Created by Qbian on 2017/3/23.
 */
@Component
public class MessagesUtil implements ApplicationContextAware {

    public static Environment environment;
    public static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        MessagesUtil.context = applicationContext;
        MessagesUtil.environment = applicationContext.getEnvironment();
    }

    /**
     * 根据错误码获取属性文件的错误提示 message
     * @param errorCode 错误码
     * @return 错误提示 message
     */
    public static String getErrorMsg(int errorCode) {
        return environment.getProperty(Constants.System.ERROR_CODE_PREFIX + errorCode);
    }

}

package com.yuhi.base.controller;

import com.yuhi.base.web.WebUtil;
import com.yuhi.base.web.message.I18Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 李森林 on 2017-05-02.
 */
public class BaseController {

      protected Logger logger = LoggerFactory.getLogger(this.getClass());
    protected static final String ERROR_VIEW = "/common/error";
//    protected static final Message ERROR_MSG = Message.error("common.message.error", new Object[0]);
//    protected static final Message WARN_MSG = Message.warn("common.message.warn", new Object[0]);
//    protected static final Message SUCCESS_MSG = Message.success("common.message.success", new Object[0]);
//    protected static final String CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME = "constraintViolations";

    @Value("#{configProperties['version']}")
    protected String version;

    protected String message(String code, Object... args) {
        return I18Util.getMessage(code, args);
    }

    @ModelAttribute
    public void requestAndResponse(HttpServletRequest request, HttpServletResponse response) {
        WebUtil.setResponse(response);
        WebUtil.setRequest(request);
    }

}

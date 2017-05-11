package com.yuhi.base.controller;

import com.yuhi.base.web.WebUtil;
import com.yuhi.base.web.message.I18Util;
import com.yuhi.base.web.message.Message;
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
    protected static final Message ERROR_MSG = Message.error("error");
    protected static final Message WARN_MSG = Message.warn("warn");
    protected static final Message SUCCESS_MSG = Message.success("success");
    protected static final String CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME = "constraintViolations";

    @Value("#{configProperties['version']}")
    protected String version;

    protected String message(HttpServletRequest request,String code) {
        return I18Util.getMessage(request, code);
    }

    @ModelAttribute
    public void requestAndResponse(HttpServletRequest request, HttpServletResponse response) {
        WebUtil.setResponse(response);
        WebUtil.setRequest(request);
    }

}

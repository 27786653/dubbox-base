//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yuhi.base.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import javax.mail.internet.MimeUtility;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.subject.WebSubject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

public class WebUtil {
    private static Logger logger = LoggerFactory.getLogger(WebUtil.class);
    private static final String UNKNOWN = "unknown";
    private static ThreadLocal<HttpServletRequest> servletRequest = new ThreadLocal();
    private static ThreadLocal<HttpServletResponse> servletResponse = new ThreadLocal();

    public WebUtil() {
    }

    public static void setRequest(HttpServletRequest request) {
        servletRequest.set(request);
    }

    public static void setResponse(HttpServletResponse response) {
        servletResponse.set(response);
    }

    public static HttpServletResponse getResponse() {
        return (HttpServletResponse)servletResponse.get();
    }

    public static HttpServletRequest getRequest() {
        HttpServletRequest request = (HttpServletRequest)servletRequest.get();
        HttpServletRequest requestTemp = null;
        if(request == null) {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            if(requestAttributes != null) {
                requestTemp = ((ServletRequestAttributes)requestAttributes).getRequest();
            }

            if(requestTemp == null) {
                ServletRequest servletRequest = ((WebSubject)SecurityUtils.getSubject()).getServletRequest();
                if(servletRequest != null) {
                    requestTemp = (HttpServletRequest)servletRequest;
                }
            }


            if(requestTemp != null) {
                request = requestTemp;
                servletRequest.set(requestTemp);
            }
        }

        return request;
    }

    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    public static <T> T getSessionData(String attrName) {
        return (T) getSession().getAttribute(attrName);
    }

    public static void setSessionData(String attrName, Object obj) {
        Assert.hasLength(attrName);
        getSession().setAttribute(attrName, obj);
    }

    public static Cookie getCookie(String name) {
        return WebUtils.getCookie(getRequest(), name);
    }


    public static void addCookie(String name, String value, Integer maxAge, String path, String domain, Boolean secure) {
        Assert.hasText(name);

        try {
            name = URLEncoder.encode(name, "UTF-8");
            value = URLEncoder.encode(value, "UTF-8");
            Cookie e = new Cookie(name, value);
            if(maxAge != null) {
                e.setMaxAge(maxAge.intValue());
            }

            if(StringUtils.isNotEmpty(path)) {
                e.setPath(path);
            }

            if(StringUtils.isNotEmpty(domain)) {
                e.setDomain(domain);
            }

            if(secure != null) {
                e.setSecure(secure.booleanValue());
            }

            getResponse().addCookie(e);
        } catch (UnsupportedEncodingException var7) {
            var7.printStackTrace();
        }

    }



    public static String getCookieVal(String name) {
        Assert.hasText(name);
        Cookie[] cookies = getRequest().getCookies();
        if(cookies != null) {
            try {
                name = URLEncoder.encode(name, "UTF-8");
                Cookie[] e = cookies;
                int var3 = cookies.length;

                for(int var4 = 0; var4 < var3; ++var4) {
                    Cookie cookie = e[var4];
                    if(name.equals(cookie.getName())) {
                        return URLDecoder.decode(cookie.getValue(), "UTF-8");
                    }
                }
            } catch (UnsupportedEncodingException var6) {
                var6.printStackTrace();
            }
        }

        return null;
    }

    public static void setCookie(String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(-1);
        getResponse().addCookie(cookie);
    }

    public static boolean isAjaxRequest() {
        return isAjaxRequest(getRequest());
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestedHeader = request.getHeader("X-Requested-With");
        String accept = request.getHeader("accept");
        boolean isAjax = Boolean.FALSE.booleanValue();
        if(StringUtils.isNotBlank(accept) && accept.contains("application/json") || StringUtils.isNotBlank(requestedHeader) && requestedHeader.equalsIgnoreCase("XMLHttpRequest")) {
            isAjax = Boolean.TRUE.booleanValue();
        }

        if(!isAjax) {
            String format = request.getParameter("format");
            String callback = request.getParameter("callback");
            isAjax = StringUtils.isNotBlank(format) && format.equalsIgnoreCase("json");
            isAjax = isAjax?isAjax:request.getRequestURI().contains(".json");
            isAjax = isAjax?isAjax:StringUtils.isNotBlank(callback);
        }

        return isAjax;
    }

    public static void responseJson(String json) {
        responseJson(getResponse(), json);
    }

    public static void responseJson(HttpServletResponse response, String json) {
        responseJson(response, getRequest(), json);
    }

    public static void responseJson(HttpServletResponse response, HttpServletRequest request, String json) {
        try {
            response.setContentType("application/json; charset=utf-8");
            PrintWriter e = response.getWriter();
            String callback = request.getParameter("callback");
            if(StringUtils.isNotBlank(callback)) {
                json = callback + "(" + json + ")";
            }

            e.write(json);
            e.flush();
            e.close();
        } catch (IOException var5) {
            logger.error("responseJson", var5);
        }

    }

    public static String encodingFileName(String fileName, HttpServletRequest request) {
        String agent = request.getHeader("USER-AGENT");

        try {
            if(agent != null && agent.contains("MSIE")) {
                String ex = URLEncoder.encode(fileName, "UTF-8");
                ex = StringUtils.replace(ex, "+", "%20");
                if(ex.length() > 150) {
                    ex = new String(fileName.getBytes("GB2312"), "ISO8859-1");
                    ex = StringUtils.replace(ex, " ", "%20");
                }

                return ex;
            } else {
                return agent != null && agent.contains("Mozilla")?MimeUtility.encodeText(fileName, "UTF-8", "B"):fileName;
            }
        } catch (Exception var4) {
            return fileName;
        }
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if(StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }

        if(StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if(StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }

        if(StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if(StringUtils.isNotBlank(ip) && (ip.contains("127.0.0.1") || ip.contains("0:0:0:0:0:0:0:1") || ip.contains("localhost"))) {
//            ip = request.getIPAddress();
            // 本地访问
        }

        return ip;
    }
}

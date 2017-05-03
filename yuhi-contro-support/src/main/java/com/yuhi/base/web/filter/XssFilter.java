//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yuhi.base.web.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Objects;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

public class XssFilter implements Filter {
    public XssFilter() {
    }

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        XssFilter.XssHttpServletRequestWrapper xssRequest = new XssFilter.XssHttpServletRequestWrapper((HttpServletRequest)request);
        chain.doFilter(xssRequest, response);
    }

    public void destroy() {
    }

    class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
        public XssHttpServletRequestWrapper(HttpServletRequest request) {
            super(request);
        }

        public ServletInputStream getInputStream() throws IOException {
            return super.getInputStream();
        }

        public long getDateHeader(String name) {
            return super.getDateHeader(name);
        }

        public Enumeration getHeaders(String name) {
            return super.getHeaders(name);
        }

        public Enumeration getHeaderNames() {
            return super.getHeaderNames();
        }

        public int getIntHeader(String name) {
            return super.getIntHeader(name);
        }

        public String getContextPath() {
            return super.getContextPath();
        }

        public String getQueryString() {
            return super.getQueryString();
        }

        public String getRequestURI() {
            return super.getRequestURI();
        }

        public StringBuffer getRequestURL() {
            return super.getRequestURL();
        }

        public Object getAttribute(String name) {
            Object result = super.getAttribute(name);
            if(result instanceof String) {
                result = StringEscapeUtils.escapeHtml4(Objects.toString(result));
            }

            return result;
        }

        public String[] getParameterValues(String name) {
            return super.getParameterValues(name);
        }

        public String getHeader(String name) {
            String value = super.getHeader(name);
            if(StringUtils.isBlank(value)) {
                return value;
            } else {
                value = StringEscapeUtils.escapeHtml4(value);
                return value;
            }
        }

        public String getParameter(String name) {
            String value = super.getParameter(name);
            if(StringUtils.isBlank(value)) {
                return value;
            } else {
                value = StringEscapeUtils.escapeHtml4(value);
                value = StringEscapeUtils.escapeXml(value);
                return value;
            }
        }
    }
}

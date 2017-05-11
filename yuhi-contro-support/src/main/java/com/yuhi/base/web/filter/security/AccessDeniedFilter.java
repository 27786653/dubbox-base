//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yuhi.base.web.filter.security;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class AccessDeniedFilter implements Filter {
    private static final String errMsg = "您无权访问该资源！";

    public AccessDeniedFilter() {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse)resp;
        response.addHeader("Power-By", "catt");
        response.sendError(403, "您无权访问该资源！");
    }

    public void init(FilterConfig config) throws ServletException {
    }
}

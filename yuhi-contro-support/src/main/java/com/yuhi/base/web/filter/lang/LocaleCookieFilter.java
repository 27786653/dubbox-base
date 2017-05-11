package com.yuhi.base.web.filter.lang;

import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;


@WebFilter("/LocaleFilter")
public class LocaleCookieFilter implements Filter {


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Locale locale =Locale.SIMPLIFIED_CHINESE;
		Cookie[] cookies = ((HttpServletRequest)request).getCookies();
		if(cookies != null && cookies.length > 0){
			for (Cookie cookie : cookies) {
				if("lang".equals(cookie.getName())){
					if("en".equals(cookie.getValue()))
						locale =Locale.US;
				}
			}
		}
		((HttpServletRequest)request).getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

}

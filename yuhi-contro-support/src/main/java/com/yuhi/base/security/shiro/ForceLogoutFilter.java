package com.yuhi.base.security.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import com.yuhi.base.Constants;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ForceLogoutFilter extends AccessControlFilter {  
    @Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Session session = getSubject(request, response).getSession(false);  
        if(session == null) {  
            return true;  
        }  
        return session.getAttribute(Constants.System.SESSION_FORCE_LOGOUT_KEY) == null;
    }  
    
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        try {  
            getSubject(request, response).logout();//强制退出  
        } catch (Exception e) {/*ignore exception*/}
        String loginUrl = getLoginUrl() + (getLoginUrl().contains("?") ? "&" : "?") +
        		Constants.System.SESSION_FORCE_LOGOUT_PARAM +"=1";
        WebUtils.issueRedirect(request, response, loginUrl);  
        return false;  
    }  
    
    
}   

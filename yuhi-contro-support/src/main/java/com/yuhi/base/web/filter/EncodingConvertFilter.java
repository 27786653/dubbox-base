//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yuhi.base.web.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

public class EncodingConvertFilter extends OncePerRequestFilter {
    private String fromEncoding = "ISO-8859-1";
    private String toEncoding = "UTF-8";

    public EncodingConvertFilter() {
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        if(request.getMethod().equalsIgnoreCase("GET")) {
            Iterator it = request.getParameterMap().values().iterator();

            while(it.hasNext()) {
                String[] strs = (String[])((String[])it.next());
                int i = 0;

                for(int l = strs.length; i < l; ++i) {
                    try {
                        strs[i] = new String(strs[i].getBytes(this.fromEncoding), this.toEncoding);
                    } catch (UnsupportedEncodingException var9) {
                        var9.printStackTrace();
                    }
                }
            }
        }

        chain.doFilter(request, response);
    }

    public String getFromEncoding() {
        return this.fromEncoding;
    }

    public void setFromEncoding(String fromEncoding) {
        this.fromEncoding = fromEncoding;
    }

    public String getToEncoding() {
        return this.toEncoding;
    }

    public void setToEncoding(String toEncoding) {
        this.toEncoding = toEncoding;
    }
}

package com.shiro.filter;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

public class CORSAuthenticationFilter extends FormAuthenticationFilter {

    @Value("${baseUrl}")
    private String baseUrl;

    //判断是否登录，如果登录过
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (((HttpServletRequest) request).getMethod().toUpperCase().equals("OPTIONS")) {
            return true;
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
//       WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
        WebUtils.toHttp(response).addHeader("statusCode","401");
       return false;
    }






}

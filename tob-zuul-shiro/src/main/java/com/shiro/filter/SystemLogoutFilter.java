package com.shiro.filter;

import com.shiro.cache.RedisCacheManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class SystemLogoutFilter extends LogoutFilter{
	
	/** 踢出后到的地址 */
    private String redirectUrl;
    
    private SessionManager sessionManager;
    
    private RedisCacheManager cacheManager;

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public void setSessionManager(SessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}

	public void setCacheManager(RedisCacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}


	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		//在这里执行退出系统前需要清空的数据  
        Subject subject=getSubject(request,response);  
        subject.logout();  
		
		// TODO Auto-generated method stub
		return super.preHandle(request, response);
	}
	
}

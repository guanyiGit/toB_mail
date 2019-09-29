package com.shiro.session;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

public class StatelessSessionManager extends DefaultWebSessionManager{
	
    /**
     * 这个是客户端请求给服务端带的header
     */
    public final static String AUTHORIZATION  = "Authorization";
    public final static Logger LOG = LoggerFactory.getLogger(StatelessSessionManager.class);
    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";
    
    /**
     * 重写getSessionId,分析请求头中的指定参数，做用户凭证sessionId
     */
	@Override
	protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
		//获取客户端请求头中的token
		String id = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
		System.out.println("id："+id);
		if (!StringUtils.isEmpty(id)) {
			 //如果没有携带id参数则按照父类的方式在cookie进行获取
			 //  System.out.println("super："+super.getSessionId(request, response));
			return super.getSessionId(request, response);
		}else {
			 //如果请求头中有 Authorization 则其值为sessionId
			 request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
			 request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
			 request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID,Boolean.TRUE);
			 return id;
		}
	}
	
}

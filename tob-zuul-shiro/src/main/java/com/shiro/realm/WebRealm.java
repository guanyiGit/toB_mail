//package com.soholy.dogmanager.shiro.realm;
//
//import org.apache.log4j.Logger;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.AuthenticationInfo;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authc.DisabledAccountException;
//import org.apache.shiro.authc.SimpleAuthenticationInfo;
//import org.apache.shiro.authc.UnknownAccountException;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.cache.Cache;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.apache.shiro.subject.SimplePrincipalCollection;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.soholy.dogmanager.entity.TUsers;
//import com.soholy.dogmanager.login.service.UserService;
//import com.soholy.dogmanager.utils.serializable.MySimpleByteSource;
//
//public class WebRealm extends AuthorizingRealm{
//	
//private static Logger logger = Logger.getLogger(AuthorizingRealm.class);
//
//@Autowired
//private UserService userService;
//
//// 授权
//@Override
//protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//	
//	return null;
//}
//
//// 认证
//@Override
//protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//	System.err.println("shiro web 认证  run...");
//	// 1. 把 AuthenticationToken 转换为 UsernamePasswordToken
//	UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
//	try {
//		// 通过username查询出当前用户
//		String username = usernamePasswordToken.getUsername();
//		TUsers user = userService.getUserByUserName(username);
//		if (user == null || user.equals("")) {
//			throw new UnknownAccountException("用户名或密码错误！！"); // 用户不存在
//		} 
//		// 判断当前用户是否可用
//		if (user.getuStatus() == 1) {
//			throw new DisabledAccountException("用户已被禁用！！"); // 用户禁用
//		}
//		// 6. 根据用户的情况, 来构建 AuthenticationInfo 对象并返回.
//		// 通常使用的实现类为:SimpleAuthenticationInfo
//		// 以下信息是从数据库中获取的.
//		// 1). principal: 认证的实体信息. 可以是 username, 也可以是数据表对应的用户的实体类对象.
//		Object principal = user;
//		// 2). credentials: 密码. MD5盐值加密
//		Object credentials = user.getPassword();
//		// 3). realmName: 当前 realm 对象的 name. 调用父类的 getName() 方法即可
//		String realmName = this.getName();
//		// 4.盐值
//		MySimpleByteSource mySimpleByteSource = new MySimpleByteSource(usernamePasswordToken.getUsername());
//		//ByteSource credentialsSalt = ByteSource.Util.bytes(usernamePasswordToken.getUsername());
//		AuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, mySimpleByteSource, realmName);
//		return info;
//	} catch (Exception e) {
//		System.out.println(e.getClass().getName());
//		return null;
//	}
//}
//
// /**
// * 清除所有用户的缓存
// */
//public void clearAuthorizationInfoCache() {
//    Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
//    if(cache!=null) {
//        cache.clear();
//    }
//}
//
//
///**
// * 指定principalCollection 清除
// */
//public void clearCachedAuthorizationInfo(PrincipalCollection principalCollection) {
//	SimplePrincipalCollection principals = new SimplePrincipalCollection(
//			principalCollection, getName());
//	super.clearCachedAuthorizationInfo(principals);
//} 
//
//
//}
package com.shiro.realm;

import com.RBAC.domain.RoleDO;
import com.RBAC.domain.UserDO;
import com.RBAC.service.ResourceInfoService;
import com.RBAC.service.UserInfoService;
import com.sys.config.ApplicationContextRegister;
import com.utils.ShiroUtils;
import com.utils.serializable.MySimpleByteSource;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;


public class WebRealm extends AuthorizingRealm {

	private static Logger logger = Logger.getLogger(WebRealm.class);

	@Autowired
	private UserInfoService userInfoService;

   // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        Long userId = ShiroUtils.getUserId();
        ResourceInfoService menuService = ApplicationContextRegister.getBean(ResourceInfoService.class);
        Set<String> perms = menuService.listPerms(Integer.parseInt(userId.toString()));
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Map<String, Object> map = new HashMap<>(16);
        UserDO user = ShiroUtils.getUser();
        List<RoleDO> roles = user.getRoles();
        List<String> listrole = new ArrayList<>();
        if(roles.size()>0){
            roles.stream().forEach(role ->{
                listrole.add(role.getRoleName());
            });
        }
        info.setStringPermissions(perms);
        info.addRoles(listrole);
        return info;
    }

	// 认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.err.println("shiro web 认证  run...");
		// 1. 把 AuthenticationToken 转换为 UsernamePasswordToken
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		// 通过username查询出当前用户
		String username = usernamePasswordToken.getUsername();
        UserDO userDO = userInfoService.getUserByUserName(username);

        if(userDO.getStatus() == 1){
            throw new DisabledAccountException();
        }

        // 6. 根据用户的情况, 来构建 AuthenticationInfo 对象并返回.
		// 通常使用的实现类为:SimpleAuthenticationInfo
		// 以下信息是从数据库中获取的.
//		// 1). principal: 认证的实体信息. 可以是 username, 也可以是数据表对应的用户的实体类对象.
		Object principal = userDO;
		// 2). credentials: 密码. MD5盐值加密
		Object credentials = userDO.getPassword();
		// 3). realmName: 当前 realm 对象的 name. 调用父类的 getName() 方法即可
		String realmName = this.getName();
		// 4.盐值
		MySimpleByteSource mySimpleByteSource = new MySimpleByteSource(usernamePasswordToken.getUsername());
		AuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, mySimpleByteSource, realmName);
		return info;
	}

	 /**
     * 重写方法,清除当前用户的的 授权缓存
     * @param principals
     */
    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    /**
     * 重写方法，清除当前用户的 认证缓存
     * @param principals
     */
    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }
    
    
    /**
     * 自定义方法：清除所有 授权缓存
     */
    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    /**
     * 自定义方法：清除所有 认证缓存
     */
    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    /**
     * 自定义方法：清除所有的  认证缓存  和 授权缓存
     */
    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }
}

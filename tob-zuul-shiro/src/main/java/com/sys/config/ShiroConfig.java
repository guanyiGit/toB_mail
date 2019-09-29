
package com.sys.config;

import com.shiro.cache.RedisCacheManager;
import com.shiro.filter.CORSAuthenticationFilter;
import com.shiro.filter.KickoutSessionControlFilter;
import com.shiro.realm.WebRealm;
import com.shiro.redis.impl.RedisManager;
import com.shiro.session.LocalRedisSessionDAO;
import com.shiro.session.RedisSessionDao;
import com.shiro.session.SessionListenerImpl;
import com.shiro.session.StatelessSessionManager;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.ClassPathResource;
import redis.clients.jedis.JedisPoolConfig;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @author bootdo 1992lcg@163.com
 */
@Configuration
public class ShiroConfig {

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.cache.type}")
    private String cacheType;

    @Value("${server.session-timeout}")
    private int tomcatTimeout;


    /**
     * 配置Session ID生成器
     *
     * @return
     */
    @Bean
    public JavaUuidSessionIdGenerator sessionIdGenerator() {
        return new JavaUuidSessionIdGenerator();
    }

    /**
     * 会话Cookie模板
     * @return
     */
    @Bean
    public SimpleCookie sessionIdCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("tob-cookie"); //  cookie的name,对应的默认是 JSESSIONID
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(-1);  //默认-1
        simpleCookie.setPath("/");    //  path为 / 用于多个系统共享JSESSIONID
        return simpleCookie;
    }

    /**
     * 会话验证调度器
     *
     * @return
     */
    @Bean
    public ExecutorServiceSessionValidationScheduler sessionValidationScheduler(){
        ExecutorServiceSessionValidationScheduler  sessionValidationScheduler = new ExecutorServiceSessionValidationScheduler();
        sessionValidationScheduler.setSessionManager(sessionManager());
        //间隔多少时间检查，不配置是60分钟
        sessionValidationScheduler.setInterval(3600000);
        return sessionValidationScheduler;
    }

    /**
     * realm认证类
     * @return
     */
    @Bean
    public WebRealm webRealm() {
        WebRealm webRealm = new WebRealm();
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("MD5");
        matcher.setHashIterations(1);
        webRealm.setCredentialsMatcher(matcher);
        //启用缓存，默认关闭
        webRealm.setCachingEnabled(true);
        //启用身份验证缓存，即缓存AuthenticationInfo，默认false
        webRealm.setAuthenticationCachingEnabled(false);
        //缓存AuthenticationInfo信息的缓存名称
        webRealm.setAuthenticationCacheName("AuthenticationInfo");
        //启用授权缓存，即缓存AuthorizationInfo的信息，默认为true
        webRealm.setAuthorizationCachingEnabled(true);
        //缓存AuthorizationInfo信息的缓存名称
        webRealm.setAuthorizationCacheName("AuthorizationInfo");
        return webRealm;
    }

    /**
     * 配置认证策略
     *
     * @return
     */
    @Bean
    public ModularRealmAuthenticator authenticator(){
        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
        /**
         * AtLeastOneSuccessfulStrategy 至少一条满足的情况
         * AllSuccessfulStrategy 所有都满足的情况
         * FirstSuccessfulStrategy 第一条满足的情况
         */
        authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return authenticator;
    }

    /**
     * shiro缓存管理器 使用redis
     * cacheManager 缓存 redis实现
     * 使用的是shiro-redis开源插件
     * @return
     */
    @Bean
    public RedisCacheManager redisCacheManager(){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        redisCacheManager.setPrincipalIdFieldName("username");
        return redisCacheManager;
    }

    /**
     * 配置shiro redisManager
     *
     * @return
     */
    @Bean
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
//        redisManager.setExpire(1800);// 配置缓存过期时间
        redisManager.setTimeout(1800);
//        redisManager.setPassword(password);
        redisManager.setJedisPoolConfig(poolConfig());
        return redisManager;
    }

    @Bean
    public JedisPoolConfig poolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(100);
        jedisPoolConfig.setMaxWaitMillis(1000);
        jedisPoolConfig.setTestOnBorrow(true);
        return  jedisPoolConfig;
    }
    /**
     * 配置本地缓存管理
     * @return
     */
    @Bean
    public EhCacheManagerFactoryBean nativeEhCacheManager(){
        EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
        factoryBean.setConfigLocation(new ClassPathResource("config/ehcache.xml"));
        factoryBean.setShared(true);
        return factoryBean;
    }

    /**
     * shiro-chcache缓存管理器
     * @return
     */
    @Bean
    public EhCacheManager shiroEhCacheManager(){
        EhCacheManager ehCacheManager = new EhCacheManager();
        //通过EhCacheManagerFactoryBean.getObject()获取cacheManager对象
        ehCacheManager.setCacheManager(nativeEhCacheManager().getObject());
        return  ehCacheManager;
    }

    @Bean
    public RedisSessionDao redisSessionDao(){
        RedisSessionDao redisSessionDao = new RedisSessionDao();
        redisSessionDao.setRedisManager(redisManager());
        return redisSessionDao;
    }

    @Bean
    public LocalRedisSessionDAO localRedisSessionDAO(){
        LocalRedisSessionDAO localRedisSessionDAO = new LocalRedisSessionDAO();
        localRedisSessionDAO.setRedisSessionDao(redisSessionDao());
        localRedisSessionDAO.setCacheManager(shiroEhCacheManager());
        localRedisSessionDAO.setActiveSessionsCacheName("shiroSessionCache");
        return localRedisSessionDAO;
    }


    /**
     * shiro session的管理
     */
    @Bean
    public DefaultWebSessionManager sessionManager(){
        StatelessSessionManager sessionManager = new StatelessSessionManager();
//      DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        //配置sesionDao
        sessionManager.setSessionDAO(localRedisSessionDAO());
        //配置本地内存ehcache
        sessionManager.setCacheManager(shiroEhCacheManager());
        //加载session监听
        Collection<SessionListener> listeners = new ArrayList<SessionListener>();
        listeners.add(sessionListenerImpl());
        sessionManager.setSessionListeners(listeners);
        sessionManager.setSessionIdCookie(sessionIdCookie());
        //设置超时时间
        sessionManager.setGlobalSessionTimeout(172800000);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionIdCookieEnabled(true);
        return  sessionManager;
    }

    @Bean
    public SessionListenerImpl sessionListenerImpl(){
        return new SessionListenerImpl();
    }


    /**
     * 前后端分离项目中，由于跨域，会导致复杂请求，即会发送preflighted request，
     * 这样会导致在GET／POST等请求之前会先发一个OPTIONS请求，但OPTIONS请求并不带shiro的'Authorization'字段（shiro的Session），
     * 即OPTIONS请求不能通过shiro验证，会返回未认证的信息。
     *
     * 解决方法：给shiro增加一个过滤器，过滤OPTIONS请求
     * @return
     */
    public CORSAuthenticationFilter corsAuthenticationFilter(){
        return new CORSAuthenticationFilter();
    }

    /**
     * 并发登录控制
     * @return
     */
    public KickoutSessionControlFilter kickoutSessionControlFilter(){
        KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
        kickoutSessionControlFilter.setCacheManager(redisCacheManager());
        kickoutSessionControlFilter.setSessionManager(sessionManager());
        kickoutSessionControlFilter.setKickoutAfter(false);
        kickoutSessionControlFilter.setMaxSession(1);
        kickoutSessionControlFilter.setKickoutUrl("/login");
        return  kickoutSessionControlFilter;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置realm.
        securityManager.setRealm(webRealm());
        // 自定义缓存实现 使用redis
        if (Constant.CACHE_TYPE_REDIS.equals(cacheType)) {
            securityManager.setCacheManager(redisCacheManager());
        }
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    /**
     * 配置shiro过滤器
     * @param securityManager
     * @return
     */
    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //默认跳转到登陆页面
        shiroFilterFactoryBean.setLoginUrl("/biz/login");
        //登陆成功后的页面
        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        //自定义过滤器
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("kickout",kickoutSessionControlFilter());
        filterMap.put("corsAuthenticationFilter",corsAuthenticationFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        //权限控制map
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/biz/login", "anon");
        filterChainDefinitionMap.put("/favicon.ico", "anon");
//        filterChainDefinitionMap.put("/biz/tob-budget/subject/subList", "anon");
//        filterChainDefinitionMap.put("/css/**", "anon");
//        filterChainDefinitionMap.put("/js/**", "anon");
//        filterChainDefinitionMap.put("/fonts/**", "anon");
//        filterChainDefinitionMap.put("/img/**", "anon");
//        filterChainDefinitionMap.put("/images/**", "anon");
//        filterChainDefinitionMap.put("/docs/**", "anon");
//        filterChainDefinitionMap.put("/druid/**", "anon");an
//        filterChainDefinitionMap.put("/upload/**", "anon");
//        filterChainDefinitionMap.put("/files/**", "anon");
        filterChainDefinitionMap.put("/**", "corsAuthenticationFilter,kickout");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        return  new DefaultAdvisorAutoProxyCreator();
    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }








//    @Bean
//    public SessionDAO sessionDAO() {
//        if (Constant.CACHE_TYPE_REDIS.equals(cacheType)) {
//            return redisSessionDAO();
//        } else {
//            return new MemorySessionDAO();
//        }
//    }

    /**
     * shiro session的管理
     */
//    @Bean
//    public DefaultWebSessionManager sessionManager() {
//        StatelessSessionManager sessionManager = new StatelessSessionManager();
////        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
//        sessionManager.setGlobalSessionTimeout(tomcatTimeout * 1000);
//        sessionManager.setSessionDAO(sessionDAO());
//        Collection<SessionListener> listeners = new ArrayList<SessionListener>();
//        listeners.add(new BDSessionListener());
//        sessionManager.setSessionListeners(listeners);
//        return sessionManager;
//    }

//    @Bean
//    public EhCacheManager ehCacheManager() {
//        EhCacheManager em = new EhCacheManager();
//        em.setCacheManager(cacheManager());
//        return em;
//    }

//    @Bean("cacheManager2")
//    CacheManager cacheManager() {
//        return CacheManager.create();
//    }


}

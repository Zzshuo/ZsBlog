package com.zs.blog.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zshuo
 * @date 2018/10/10
 **/
@Slf4j
@Configuration
public class ShiroConfig {

    @Autowired
    private ShiroProperties shiroProperties;

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //拦截器.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        //注意过滤器配置顺序 不能颠倒
        //anon. 配置不会被拦截的请求 顺序判断
        filterChainDefinitionMap.put("/admin/user/logout", "anon");
        filterChainDefinitionMap.put("/admin/user/login", "anon");
        //authc. 配置拦截的请求
        filterChainDefinitionMap.put("/admin/**", "authc");
        //配置shiro默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
        shiroFilterFactoryBean.setLoginUrl("/admin/user/unauth");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        log.info("shiroFilter注册完成");
        return shiroFilterFactoryBean;
    }

    /**
     * 注入SecurityManager
     * 权限管理，这个类组合了登陆，登出，权限，session的处理
     */
    @Bean
    public SessionsSecurityManager securityManager(ShiroRealm shiroRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(shiroRealm);
        manager.setSessionManager(sessionManager());
        //注入记住我管理器
        manager.setRememberMeManager(rememberMeManager());
        log.info("securityManager注册完成");
        return manager;
    }

    /**
     * 密码凭证匹配器，作为自定义认证的基础
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了 ）
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(shiroProperties.getHashAlgorithmName());
        hashedCredentialsMatcher.setHashIterations(shiroProperties.getHashIterations());
        return hashedCredentialsMatcher;
    }

    /**
     * 2.自定义sessionManager，用户的唯一标识，即Token或Authorization的认证
     */
    public SessionManager sessionManager() {
        MySessionManager mySessionManager = new MySessionManager();
        mySessionManager.setSessionDAO(redisSessionDAO());
        return mySessionManager;
    }

    /**
     * 配置shiro redisManager 使用的是shiro-redis开源插件
     *
     * @return
     */
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(shiroProperties.getHost() + ":" + shiroProperties.getPort());
        redisManager.setPassword(shiroProperties.getPassword());
        redisManager.setTimeout(shiroProperties.getTimeout());
        return redisManager;
    }

    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        // 自定义session管理 使用redis
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * 此处对应前端“记住我”的功能，获取用户关联信息而无需登录
     */
    public SimpleCookie rememberMeCookie() {
        //这个参数是cookie的名称，对应前端的checkbox的name = remember
        SimpleCookie simpleCookie = new SimpleCookie("remember");
        simpleCookie.setMaxAge(shiroProperties.getExpire());
        return simpleCookie;
    }

    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey(Base64.decode("one"));
        return cookieRememberMeManager;
    }

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        //不需要在此处配置权限页面,因为上面的ShiroFilterFactoryBean已经配置过,但是此处必须存在,因为shiro-spring-boot-web-starter或查找此Bean,没有会报错
        return new DefaultShiroFilterChainDefinition();
    }
}

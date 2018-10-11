package com.zs.web.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * @author zshuo
 * @date 2018/10/10
 **/
@Slf4j
@Configuration
public class ShiroConfig {

    /**
     * 注入Realm
     *
     * @return ShiroRealm
     */
    @Bean
    public ShiroRealm shiroRealm() {
        ShiroRealm shiroRealm = new ShiroRealm();
        log.info("shiroRealm注册完成");
        return shiroRealm;
    }


    /**
     * 注入SecurityManager
     *
     * @param shiroRealm
     * @return SecurityManager
     */
    @Bean
    public SessionsSecurityManager securityManager(ShiroRealm shiroRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(shiroRealm);
        log.info("securityManager注册完成");
        return manager;
    }

    /**
     * 注入Filter
     *
     * @param securityManager
     * @return ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager);
        // 配置登录的url和登录成功的url
        filterFactoryBean.setLoginUrl("/login");
        filterFactoryBean.setSuccessUrl("/admin");
        // 配置未授权跳转页面
        filterFactoryBean.setUnauthorizedUrl("/errorPage/403");
        // 配置访问权限
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 表示可以匿名访问
        filterChainDefinitionMap.put("/**", "anon");
        // 表示admin权限才可以访问，多个加引号用逗号相隔
        filterChainDefinitionMap.put("/admin/**", "roles[admin]");
        // 表示需要认证才可以访问
        filterChainDefinitionMap.put("/admin/*", "authc");
        filterChainDefinitionMap.put("/admin/**", "authc");
        filterChainDefinitionMap.put("/admin/*.*", "authc");
        filterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        log.info("shiroFilter注册完成");
        return filterFactoryBean;
    }

}

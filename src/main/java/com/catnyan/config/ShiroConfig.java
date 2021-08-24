package com.catnyan.config;

import com.catnyan.shiro.ShiroRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * Created by Cat-Nyan
 * on 2021/8/23 14:20
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        //权限设置
        LinkedHashMap<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/main","authc");
        filterMap.put("/manage", "perms[manage]");
        filterMap.put("/administrator", "roles[admin]");
        factoryBean.setFilterChainDefinitionMap(filterMap);
        //设置登录界面
        factoryBean.setLoginUrl("/login");
        //设置未授权页面
        factoryBean.setUnauthorizedUrl("/unauthc");

        return factoryBean;
    }

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(ShiroRealm shiroRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm);
        return securityManager;
    }
}

package com.drww.shiro;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * className:ShiroConfig
 * discriptoin:
 * author:丁启斌
 * createTime:2018-11-13 21:14
 */
@Configuration
public class ShiroConfig {
    //创建shiroFilterFactoryBean

    /**
     * 常用过滤器
     * anon:无需认证
     * authc: 必须认证才能访问
     * user: 如果使用rememberNe的功能可以直接访问
     * perms: 该资源必须授予资源权限
     * role: 该资源必须得到角色权限才可以访问
     */
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //添加Shiro 内置过滤器
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/toUserLogin", "anon");
        filterMap.put("/css/**", "anon");
        filterMap.put("/css1/**", "anon");
        filterMap.put("/fonts-awesome/**", "anon");
        filterMap.put("/images/**", "anon");
        filterMap.put("/js1/**", "anon");
        filterMap.put("/script/**", "anon");
        filterMap.put("/customer/**", "anon");
        filterMap.put("/*/*", "authc");
        //设置未授权的页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/toUserLogin111");
        //修改调整的登录页面
        shiroFilterFactoryBean.setLoginUrl("/toUserLogin");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }


    @Bean(name = "defaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        System.out.println("-----------------");
        //创建DefaultWebSecuritManager
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(userRealm);
       // securityManager.setCacheManager(ehCacheManager());//注入缓存对象。
        /*System.out.println("开始存入cookie");
        securityManager.setRememberMeManager(cookieRememberMeManager()); //注入rememberMeManager;
        System.out.println("结束存入cookie");*/
        return securityManager;
    }

    //因为我们的密码是加过密的，所以，如果要Shiro验证用户身份的话，需要告诉它我们用的是md5加密的，并且是加密了两次。
    // 同时我们在自己的Realm中也通过SimpleAuthenticationInfo返回了加密时使用的盐。
    // 这样Shiro就能顺利的解密密码并验证用户名和密码是否正确了。

    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");
        //散列算法:这里使用MD5算法;
        System.out.println("运用的md5加密");
        credentialsMatcher.setHashIterations(2);
        //散列的次数，比如散列两次，相当于 md5(md5(""));
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }


    //穿件realm
    @Bean(name = "userRealm")
    public UserRealm getRealm(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher matcher) {
        UserRealm userRealm = new UserRealm();
        userRealm.setAuthorizationCachingEnabled(false);
        userRealm.setCredentialsMatcher(matcher);
        return userRealm;
    }

    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }

    /*@Bean
    public EhCacheManager ehCacheManager() {
        System.out.println("ShiroConfiguration.getEhCacheManager()");
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return ehCacheManager;
    }*/


    //cookie对象;
   /* @Bean
    public SimpleCookie rememberMeCookie() {
        System.out.println("ShiroConfiguration.rememberMeCookie()");
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setHttpOnly(true);
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }*/


    //cookie管理对象;
   /* @Bean
    public CookieRememberMeManager cookieRememberMeManager() {
        System.out.println("ShiroConfiguration.rememberMeManager()");
        CookieRememberMeManager manager = new CookieRememberMeManager();
        manager.setCookie(rememberMeCookie());
        //这个地方有点坑，不是所有的base64编码都可以用，长度过大过小都不行，没搞明白，官网给出的要么0x开头十六进制，要么base64
        manager.setCipherKey(
                Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        return manager;
    }*/


}


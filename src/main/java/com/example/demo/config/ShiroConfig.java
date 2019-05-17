package com.example.demo.config;

import java.util.LinkedHashMap;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.example.demo.util.MyShiroRealm;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;

@Configuration
public class ShiroConfig {

	@Bean(name="shiroFilter")
	public ShiroFilterFactoryBean shirFilter(@Qualifier("securityManager")SecurityManager securityManager){
		ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
		//设置securityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 配置登录的url,如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl("/thy/static/toBackStageLogin");
		// 配置登录成功的url,登录成功后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/thy/static/main");
		//未授权界面;
		shiroFilterFactoryBean.setUnauthorizedUrl("/Unauthorized");
		
		//配置访问权限,拦截器
		LinkedHashMap<String,String> filterChainDefinitionMap =new LinkedHashMap<String,String>();
		//配置访问权限
		filterChainDefinitionMap.put("/login*","anon"); //表示可以匿名访问
		filterChainDefinitionMap.put("/loginUser","anon");
		//配置退出过滤器，具体的退出代码Shiro已经替我们实现
		filterChainDefinitionMap.put("/logout","logout");
		filterChainDefinitionMap.put("/kf/**","anon");
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/thy/static/**","anon");
		filterChainDefinitionMap.put("/thy/order/**","anon");
		filterChainDefinitionMap.put("/thy/sys/**","anon");
		filterChainDefinitionMap.put("/item_Img/**","anon");
		//<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
		//<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
		filterChainDefinitionMap.put("/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}
	
	/**
	 * 密码校验规则 HashedCredentialsMatcher
	 * 这个类是为了对密码进行编码的，
	 * 防止密码在数据库里明码报存，当前在登录认证的时候
	 * 这个类也负责对form里输入的密码进行编码
	 * 处理认证匹配处理器：如果自定义需要实现继承
	 * HashedCredentialsMatcher
	 * @return
	 */
	@Bean("hashedCredentialsMatcher")
	public HashedCredentialsMatcher hashedCredentialsMatcher(){
		System.err.println("===hashedCredentialsMatcher======");
		HashedCredentialsMatcher credentialsMatcher=new HashedCredentialsMatcher();
		//指定加密的方式为MD5
		credentialsMatcher.setHashAlgorithmName("MD5");
		//加密次数
		credentialsMatcher.setHashIterations(1024);
		credentialsMatcher.setStoredCredentialsHexEncoded(true);
		return credentialsMatcher;
	}
	
	@Bean("myShiroRealm")
	@DependsOn("lifecycleBeanPostProcessor")//可选
	public MyShiroRealm myShiroRealm(@Qualifier("hashedCredentialsMatcher")HashedCredentialsMatcher matcher){
		MyShiroRealm myShiroRealm=new MyShiroRealm();
		myShiroRealm.setAuthorizationCachingEnabled(false);
		myShiroRealm.setCredentialsMatcher(matcher);
		return myShiroRealm;
	}
	
	 /**
     * 定义安全管理器securityManager,注入自定义的realm
     * @param authRealm
     * @return
     */
    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier("myShiroRealm") MyShiroRealm myShiroRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myShiroRealm);
        return manager;
    }
    
    /**
     * Spring的一个bean , 由Advisor决定对哪些类的方法进行AOP代理 .
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    /**
     * 配置shiro跟spring的关联
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    /**
     * lifecycleBeanPostProcessor是负责生命周期的 , 初始化和销毁的类
     * (可选)
     */
    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
}

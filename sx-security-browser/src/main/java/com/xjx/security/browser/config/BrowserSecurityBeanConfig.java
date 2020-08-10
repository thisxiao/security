package com.xjx.security.browser.config;

import com.xjx.security.browser.logout.SxLogoutSuccessHandler;
import com.xjx.security.browser.session.SxExpiredSessionStrategy;
import com.xjx.security.browser.session.SxInvalidSessionStrategy;
import com.xjx.security.core.properties.SecurityProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.annotation.Resource;

/**
 * 自动注入配置类
 * @Author XJX
 * @Date 2020/7/17 15:11
 * @Version 1.0
 */
@Configuration
public class BrowserSecurityBeanConfig {

    @Resource
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(InvalidSessionStrategy.class)
    public InvalidSessionStrategy invalidSessionStrategy(){
        return new SxInvalidSessionStrategy(securityProperties.getBrowser().getSession().getSessionInvalidUrl());
    }

    @Bean
    @ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
    public SessionInformationExpiredStrategy sessionInformationExpiredStrategy(){
        return new SxExpiredSessionStrategy(securityProperties.getBrowser().getSession().getSessionInvalidUrl());
    }

    @Bean
    @ConditionalOnMissingBean(LogoutSuccessHandler.class)
    public LogoutSuccessHandler logoutSuccessHandler(){
        return new SxLogoutSuccessHandler(securityProperties);
    }
}

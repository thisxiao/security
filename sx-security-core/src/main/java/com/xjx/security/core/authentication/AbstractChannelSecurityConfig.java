package com.xjx.security.core.authentication;

import com.xjx.security.core.properties.SecurityConstants;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.annotation.Resource;

/**
 * @Author XJX
 * @Date 2020/7/14 11:14
 * @Version 1.0
 */
public class AbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    protected AuthenticationSuccessHandler szAuthenticationSuccessHandler;

    @Resource
    protected AuthenticationFailureHandler szAuthenticationFailureHandler;

    protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
        http.formLogin()
            .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
            .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
            .successHandler(szAuthenticationSuccessHandler)
            .failureHandler(szAuthenticationFailureHandler);
    }
}

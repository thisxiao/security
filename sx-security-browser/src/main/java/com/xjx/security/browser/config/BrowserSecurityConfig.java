package com.xjx.security.browser.config;

import com.xjx.security.core.authentication.AbstractChannelSecurityConfig;
import com.xjx.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.xjx.security.core.properties.SecurityConstants;
import com.xjx.security.core.properties.SecurityProperties;
import com.xjx.security.core.validate.code.config.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @Author XJX
 * @Date 2020/7/9 14:44
 * @Version 1.0
 */
@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {

    @Resource
    private SecurityProperties securityProperties;

    @Resource
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Resource
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Resource
    private DataSource dataSource;

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private SpringSocialConfigurer sxSocialSecurityConfig;

    /**
     * 登录过滤器配置
     * @param http 请求
     * @throws Exception the ex
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        applyPasswordAuthenticationConfig(http);

        http.apply(validateCodeSecurityConfig)
                .and()
            .apply(smsCodeAuthenticationSecurityConfig)
                .and()
            .apply(sxSocialSecurityConfig)
                .and()
            .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(userDetailsService)
                .and()
            .authorizeRequests()
                .antMatchers(
                    SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                    SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                    securityProperties.getBrowser().getLoginPage(),
                    SecurityConstants.DEFAULT_GET_SOCIAL_USERINFO_URL,
                    SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                    securityProperties.getBrowser().getSignUpUrl(),
                    "/user/register")
                    .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        //tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }
}

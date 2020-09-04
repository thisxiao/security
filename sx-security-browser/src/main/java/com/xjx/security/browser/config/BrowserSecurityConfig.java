package com.xjx.security.browser.config;

import com.xjx.security.core.authentication.AbstractChannelSecurityConfig;
import com.xjx.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.xjx.security.core.properties.SecurityConstants;
import com.xjx.security.core.properties.SecurityProperties;
import com.xjx.security.core.validate.code.config.ValidateCodeSecurityConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;

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

    @Resource
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Resource
    private InvalidSessionStrategy invalidSessionStrategy;

    @Resource
    private LogoutSuccessHandler logoutSuccessHandler;

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
            //短信校验相关配置
            .apply(smsCodeAuthenticationSecurityConfig)
                .and()
            //springSocial校验相关配置
            .apply(sxSocialSecurityConfig)
                .and()
            //记住我相关配置
            .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(userDetailsService)
                .and()
            //session相关的控制
            .sessionManagement()
                //指定session失效策略
                .invalidSessionStrategy(invalidSessionStrategy)
                //指定最大的session并发数量---即一个用户只能同时在一处登陆（腾讯视频的账号好像就只能同时允许2-3个手机同时登陆）
                .maximumSessions(securityProperties.getBrowser().getSession().getMaximumSession())
                //当超过指定的最大session并发数量时，阻止后面的登陆（感觉貌似很少会用到这种策略）
                .maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin())
                //超过最大session并发数量时的策略
                .expiredSessionStrategy(sessionInformationExpiredStrategy)
                .and()
                .and()
            .logout()
                //退出登录路径
                .logoutUrl("/signOut")
                .logoutSuccessHandler(logoutSuccessHandler)
                //退出登录时删除cookie里面的信息
                .deleteCookies("JSESSIONID")
                .and()
            .authorizeRequests()
                //配置不用进行认证校验的url
                .antMatchers(
                    SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                    SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                    securityProperties.getBrowser().getLoginPage(),
                    //获取第三方账号的用户信息的默认url----微信、QQ登陆没找到与本系统的关联关系时用到---此时没登陆
                    SecurityConstants.DEFAULT_GET_SOCIAL_USERINFO_URL,
                    SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                    securityProperties.getBrowser().getSignUpUrl(),
                    //session失效默认的跳转地址
                    securityProperties.getBrowser().getSession().getSessionInvalidUrl(),
                    //退出登录页面
                    securityProperties.getBrowser().getSignOutUrl(),
                    "/user/register")
                    .permitAll()
                    //指明除了上面不用认证的url外其他请求都需要认证校验
                    .anyRequest()
                .authenticated()
                .and()
            //关闭csrf
            .csrf().disable();
    }


    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        //tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }
}

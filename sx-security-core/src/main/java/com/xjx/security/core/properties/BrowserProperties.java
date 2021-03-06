package com.xjx.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author XJX
 * @Date 2020/7/10 10:06
 * @Version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "sx.security.browser")
public class BrowserProperties {
    /**指定默认的登陆页面*/
    private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;
    /**退出登录url*/
    private String signOutUrl;
    /**指定默认的处理成功与处理失败的方法*/
    private LoginType loginType = LoginType.JSON;
    /**指定默认的注册页面*/
    private String signUpUrl = SecurityConstants.DEFAULT_SIGNUP_URL;
    /**记住我的时间3600秒即1小时*/
    private int rememberMeSeconds = 3600;
    /**session相关的配置如session最大并发数量等*/
    private SessionProperties session = new SessionProperties();
}

package com.xjx.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author XJX
 * @Date 2020/7/17 14:34
 * @Version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "sx.security.browser.session")
public class SessionProperties {

    /**
     * 同一个用户在系统中的最大Session数，默认为1
     */
    private int maximumSession = 1;

    /**
     * 达到最大session时是否阻止新的登录请求，默认为false，不阻止，新的登录会将老的登录失效掉
     */
    private boolean maxSessionsPreventsLogin;

    /**
     * session失效时跳转的地址
     */
    private String sessionInvalidUrl = SecurityConstants.DEFAULT_SESSION_INVALID_URL;
}

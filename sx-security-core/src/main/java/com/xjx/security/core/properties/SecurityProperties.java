package com.xjx.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author XJX
 * @Date 2020/7/10 10:07
 * @Version 1.0
 */
@Data
@ConfigurationProperties(prefix = "sx.security")
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();

    private ValidateCodeProperties code = new ValidateCodeProperties();

    private SocialProperties social = new SocialProperties();
}

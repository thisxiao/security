package com.xjx.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author XJX
 * @Date 2020/7/15 9:58
 * @Version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "sx.security.social")
public class SocialProperties {

    /**过滤器默认拦截路劲*/
    private String filterProcessesUrl = "/auth";

    /**qq配置类*/
    private QQProperties qq = new QQProperties();

    /**weixin配置类*/
    private WeiXinProperties weixin = new WeiXinProperties();
}

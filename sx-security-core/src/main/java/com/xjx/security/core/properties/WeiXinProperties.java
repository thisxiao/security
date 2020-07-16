package com.xjx.security.core.properties;

import com.xjx.security.core.social.configutils.SocialPropertiesCommon;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author XJX
 * @Date 2020/7/16 11:05
 * @Version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "sx.security.social.weixin")
public class WeiXinProperties extends SocialPropertiesCommon {

    /**
     * 第三方登录，用来决定发起第三方登录的url，默认是weiixin
     */
    private String providerId = "weixin";
}

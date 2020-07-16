package com.xjx.security.core.social.weixin.config;

import com.xjx.security.core.properties.SecurityProperties;
import com.xjx.security.core.properties.WeiXinProperties;
import com.xjx.security.core.social.SxConnectView;
import com.xjx.security.core.social.configutils.SocialAutoConfigurerAdapter;
import com.xjx.security.core.social.weixin.connet.WeiXinConnectionFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.web.servlet.View;

import javax.annotation.Resource;

/**
 * 微信登录配置
 * @Author XJX
 * @Date 2020/7/16 15:36
 * @Version 1.0
 */
@Configuration
@ConditionalOnProperty(prefix = "sx.security.social.weixin", name = "app-id")
public class WeiXinAutoConfiguration extends SocialAutoConfigurerAdapter {

    @Resource
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        WeiXinProperties weiXinConfig = securityProperties.getSocial().getWeixin();
        return new WeiXinConnectionFactory(weiXinConfig.getProviderId(), weiXinConfig.getAppId(),
                weiXinConfig.getAppSecret());
    }

    @Bean({"connect/weixinConnected","connect/weixinConnect"})
    @ConditionalOnMissingBean(name = "weixinConnectedView")
    public View weiXinConnectedView() {
        return new SxConnectView();
    }
}

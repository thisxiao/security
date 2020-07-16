package com.xjx.security.core.social.qq.config;

import com.xjx.security.core.properties.QQProperties;
import com.xjx.security.core.properties.SecurityProperties;
import com.xjx.security.core.social.configutils.SocialAutoConfigurerAdapter;
import com.xjx.security.core.social.qq.connet.QQConnectionFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

import javax.annotation.Resource;

/**
 * @Author XJX
 * @Date 2020/7/15 10:03
 * @Version 1.0
 */
@Configuration
@ConditionalOnProperty(prefix = "sx.security.social.qq",name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Resource
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qqConfig = securityProperties.getSocial().getQq();
        return new QQConnectionFactory(qqConfig.getProviderId(),qqConfig.getAppId(),qqConfig.getAppSecret());
    }
}

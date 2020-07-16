package com.xjx.security.core.social.qq.connet;

import com.xjx.security.core.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * @Author XJX
 * @Date 2020/7/14 17:02
 * @Version 1.0
 * Description: 组装ConnectionFactory对象---》ConnectionFactory对象由ServiceProvider和Adapter对象组成
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {
    /**
     * Create a {@link OAuth2ConnectionFactory}.
     *
     * @param providerId      the provider id e.g. "qq"
     */
    public QQConnectionFactory(String providerId, String appId,String appSecret) {
        super(providerId, new QQServiceProvider(appId,appSecret), new QQAdapter());
    }
}

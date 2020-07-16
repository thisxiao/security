package com.xjx.security.core.social.qq.connet;

import com.xjx.security.core.social.qq.api.QQ;
import com.xjx.security.core.social.qq.api.impl.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * @Author XJX
 * @Date 2020/7/14 16:35
 * @Version 1.0
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    private String appId;

    /**
     * 对应于OAuth2协议第一步,即将用户导向QQ认证授权页面的url
     */
    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";

    /**
     * 对应于Oauth2协议中拿着授权码获取Access Token这一步的url
     */
    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    /**
     *
     * @param appId  template for conducting the OAuth 2 flow with the provider.
     */
    public QQServiceProvider(String appId,String appSecret) {
        super(new QQOAuth2Template(appId,appSecret,URL_AUTHORIZE,URL_ACCESS_TOKEN));
        this.appId = appId;
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken,appId);
    }
}

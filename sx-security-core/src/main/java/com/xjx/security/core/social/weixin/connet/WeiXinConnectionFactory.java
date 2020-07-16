package com.xjx.security.core.social.weixin.connet;

import com.xjx.security.core.social.weixin.api.WeiXin;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

/**
 * @Author XJX
 * @Date 2020/7/16 14:33
 * @Version 1.0
 *
 * 微信连接工厂
 */
public class WeiXinConnectionFactory extends OAuth2ConnectionFactory<WeiXin> {
    /**
     * Create a {@link OAuth2ConnectionFactory}.
     *
     * @param providerId  the provider id e.g. "facebook"
     * @param appId 服务商appId
     * @param appSecret  服务商appSecret
     */
    public WeiXinConnectionFactory(String providerId, String appId,String appSecret) {
        super(providerId, new WeiXinServiceProvider(appId,appSecret),new WeiXinAdapter());
    }

    /**
     * 由于微信的openId是和accessToken一起返回的，所以在这里直接根据accessToken设置providerUserId即可，不用像QQ那样通过QQAdapter来获取
     */
    @Override
    protected String extractProviderUserId(AccessGrant accessGrant) {
        if(accessGrant instanceof WeiXinAccessGrant){
            return ((WeiXinAccessGrant)accessGrant).getOpenId();
        }
        return null;
    }

    @Override
    public Connection<WeiXin> createConnection(AccessGrant accessGrant) {
        return new OAuth2Connection<>(getProviderId(),extractProviderUserId(accessGrant),accessGrant.getAccessToken(),
                accessGrant.getRefreshToken(),accessGrant.getExpireTime(),getOAuth2ServiceProvider(), getApiAdapter(extractProviderUserId(accessGrant)));
    }

    /* (non-Javadoc)
     * @see org.springframework.social.connect.support.OAuth2ConnectionFactory#createConnection(org.springframework.social.connect.ConnectionData)
     */
    public Connection<WeiXin> createConnection(ConnectionData data) {
        return new OAuth2Connection<>(data, getOAuth2ServiceProvider(), getApiAdapter(data.getProviderUserId()));
    }

    private ApiAdapter<WeiXin> getApiAdapter(String providerUserId) {
        return new WeiXinAdapter(providerUserId);
    }

    private OAuth2ServiceProvider<WeiXin> getOAuth2ServiceProvider() {
        return (OAuth2ServiceProvider<WeiXin>) getServiceProvider();
    }
}

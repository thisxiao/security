package com.xjx.security.core.social.weixin.connet;

import com.xjx.security.core.social.weixin.api.WeiXin;
import com.xjx.security.core.social.weixin.api.impl.WeiXinImpl;
import org.springframework.boot.web.embedded.tomcat.TomcatEmbeddedWebappClassLoader;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * @Author XJX
 * @Date 2020/7/16 14:35
 * @Version 1.0
 *
 *  微信的OAuth2流程处理器的提供器，供spring social的connect体系调用
 */
public class WeiXinServiceProvider extends AbstractOAuth2ServiceProvider<WeiXin> {

    /**
     * 微信获取授权码url
     */
    private static final String URL_AUTHORIZE = "https://open.weixin.qq.com/connect/qrconnect";

    /**
     * 微信获取access_token的url
     */
    private static final String URL_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";

    /**
     * Create a new {@link WeiXinServiceProvider}.
     *
     * @param appSecret 服务商appSecret
     * @param appId 服务商appId
     */
    public WeiXinServiceProvider(String appId,String appSecret) {
        super(new WeiXinOAuth2Template(appId,appSecret,URL_AUTHORIZE,URL_ACCESS_TOKEN));
    }

    @Override
    public WeiXin getApi(String accessToken) {
        return new WeiXinImpl(accessToken);
    }
}
